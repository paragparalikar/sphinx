import { ApplicationRef, Component, ComponentFactory, ComponentFactoryResolver, ComponentRef, ElementRef, Injector, OnDestroy, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Drawflow from 'drawflow';
import {css} from "lit-element"; 
import nodes from "src/assets/workflow-nodes.json";
import { Workflow } from '../workflow.model';
import { WorkflowService } from '../workflow.service';
import { ApprovalComponent } from './nodes/approval/approval.component';
import { DynamicNodeComponent } from './nodes/dynamic-node-component';
import { EmailComponent } from './nodes/email/email.component';
import { LdapComponent } from './nodes/ldap/ldap.component';
import { RequestComponent } from './nodes/request/request.component';
import { TransformerComponent } from './nodes/transformer/transformer.component';
export const style = css`.drawflow,.drawflow .parent-node{position:relative}.parent-drawflow{display:flex;overflow:hidden;touch-action:none;outline:0}.drawflow{width:100%;height:100%;user-select:none}.drawflow .drawflow-node{display:flex;align-items:center;position:absolute;background:#0ff;width:160px;min-height:40px;border-radius:4px;border:2px solid #000;color:#000;z-index:2;padding:15px}.drawflow .drawflow-node.selected{background:red}.drawflow .drawflow-node:hover{cursor:move}.drawflow .drawflow-node .inputs,.drawflow .drawflow-node .outputs{width:0}.drawflow .drawflow-node .drawflow_content_node{width:100%;display:block}.drawflow .drawflow-node .input,.drawflow .drawflow-node .output{position:relative;width:20px;height:20px;background:#fff;border-radius:50%;border:2px solid #000;cursor:crosshair;z-index:1;margin-bottom:5px}.drawflow .drawflow-node .input{left:-27px;top:2px;background:#ff0}.drawflow .drawflow-node .output{right:-3px;top:2px}.drawflow svg{z-index:0;position:absolute;overflow:visible!important}.drawflow .connection{position:absolute;pointer-events:none}.drawflow .connection .main-path{fill:none;stroke-width:5px;stroke:#4682b4;pointer-events:all}.drawflow .connection .main-path:hover{stroke:#1266ab;cursor:pointer}.drawflow .connection .main-path.selected{stroke:#43b993}.drawflow .connection .point{cursor:move;stroke:#000;stroke-width:2;fill:#fff;pointer-events:all}.drawflow .connection .point.selected,.drawflow .connection .point:hover{fill:#1266ab}.drawflow .main-path{fill:none;stroke-width:5px;stroke:#4682b4}.drawflow-delete{position:absolute;display:block;width:30px;height:30px;background:#000;color:#fff;z-index:4;border:2px solid #fff;line-height:30px;font-weight:700;text-align:center;border-radius:50%;font-family:monospace;cursor:pointer}.drawflow>.drawflow-delete{margin-left:-15px;margin-top:15px}.parent-node .drawflow-delete{right:-15px;top:-15px}`;

@Component({
  selector: 'app-workflow-editor',
  templateUrl: './workflow-editor.component.html',
  styleUrls: ['./workflow-editor.component.css'],
  encapsulation: ViewEncapsulation.None 
})
export class WorkflowEditorComponent implements OnInit {
 
  @ViewChild('drawFlowDiv', {read: ElementRef, static: true})
  drawFlowDiv!: ElementRef;
  
  counter: number = 0;
  nodeItems = nodes;
  drawFlow?: Drawflow;
  workflow = new Workflow();
  title = 'Create New Workflow';
  domParser = new DOMParser();
  
  constructor(
    private injector: Injector,
    private activatedRoute: ActivatedRoute,
    private applicationRef: ApplicationRef,
    private workflowService: WorkflowService,
    private componentFactoryResolver: ComponentFactoryResolver) {}

  ngOnInit(){
    this.drawFlow = new Drawflow(this.drawFlowDiv.nativeElement);
    this.drawFlow.reroute = true;
    this.drawFlow.reroute_fix_curvature = true;
    this.drawFlow.force_first_input = false;
    this.drawFlow.start();

    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.id){
          this.workflowService.findById(params.id).subscribe(
            workflow => {
              this.workflow = workflow;
              this.title = workflow.name!;
              this.drawFlow?.import({
                drawflow: {
                  Home: {
                    data: workflow.data
                  }
                }
              });

              this.nodeItems.forEach(item => {
                this.drawFlow?.getNodesFromName(item.type)
                .map(id => this.drawFlow?.getNodeFromId(id))
                .forEach(node => {
                  this.counter++;
                  const div = this.getNativeElement(node?.html);
                  this.attachComponent(node!.name, node?.data, div!);
                });
              });
              
            }
          );
        }
      }
    );
  }

  zoomIn(){ this.drawFlow?.zoom_in(); }
  zoomOut() { this.drawFlow?.zoom_out(); }

  drag(event){
    event.dataTransfer.setData('node', event.target.getAttribute('data-node'));
  }

  drop(event){
    event.preventDefault();
    const nodetype = event.dataTransfer.getData('node');
    this.addNode(nodetype, event.clientX, event.clientY);
  }

  addNode(type: string, pos_x: number, pos_y: number){
    const editor: any = this.drawFlow;
    const data = { type: type };
    pos_x = pos_x * ( editor.precanvas.clientWidth / (editor.precanvas.clientWidth * editor.zoom)) - (editor.precanvas.getBoundingClientRect().x * ( editor.precanvas.clientWidth / (editor.precanvas.clientWidth * editor.zoom)));
    pos_y = pos_y * ( editor.precanvas.clientHeight / (editor.precanvas.clientHeight * editor.zoom)) - (editor.precanvas.getBoundingClientRect().y * ( editor.precanvas.clientHeight / (editor.precanvas.clientHeight * editor.zoom)));
    const nodeItem = this.nodeItems.find(item => item.type == type);
    const id: string = 'dynamic-node-' + this.counter++;
    editor.addNode(type, nodeItem?.inputs, nodeItem?.outputs, pos_x, pos_y, type, data, `<div id="${id}"></div>`, false);
    const nativeElement = document.getElementById(id);
    this.attachComponent(type, data, nativeElement);
  }

  private attachComponent(type: string, data: any, nativeElement: HTMLElement | null){
    const componentFactory: ComponentFactory<DynamicNodeComponent> | undefined = this.resolveComponentFactory(type);
    if(componentFactory){
      const componentRef: ComponentRef<DynamicNodeComponent> = componentFactory.create(this.injector, [], nativeElement);
      componentRef.instance.setData(data);
      this.applicationRef.attachView(componentRef.hostView);
    }
  }

  private resolveComponentFactory(type: string): ComponentFactory<DynamicNodeComponent> | undefined {
    switch(type){
      case 'request' : 
        return this.componentFactoryResolver.resolveComponentFactory(RequestComponent);
      case 'email' : 
        return this.componentFactoryResolver.resolveComponentFactory(EmailComponent);
      case 'approval' : 
        return this.componentFactoryResolver.resolveComponentFactory(ApprovalComponent);
      case 'ldap' :
        return this.componentFactoryResolver.resolveComponentFactory(LdapComponent);
      case 'transformer' :
        return this.componentFactoryResolver.resolveComponentFactory(TransformerComponent);
      default :
        return undefined;
    }
  }

  private getNativeElement(html?: string){
    if(html){
      const htmlElement = this.domParser.parseFromString(html, 'text/html');
      const div: HTMLDivElement = htmlElement.getElementsByTagName('div')[0];
      const id: string | null = div.getAttribute('id');
      return document.getElementById(id!);
    }
    return undefined;
  }

  export(){
    console.log(this.drawFlow?.export());
  }

  submit(){
    if(this.drawFlow){
      this.workflow.data = this.drawFlow.export().drawflow.Home.data;
      console.log(this.workflow);
      this.workflowService.save(this.workflow).subscribe(
        result => console.log(this.workflow)
      );
    }
  }

}
