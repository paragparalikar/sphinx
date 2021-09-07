import { AfterViewInit, ApplicationRef, Component, ComponentFactoryResolver, ElementRef, Injector, Input, OnChanges, ViewChild, ViewEncapsulation } from '@angular/core';
import { Workflow } from '../../../workflows/workflow.model';
import { DrawflowManager } from 'src/app/shared/drawflow/drawflow-renderer/drawflow-manager';

@Component({
  selector: 'app-drawflow-renderer',
  templateUrl: './drawflow-renderer.component.html',
  styleUrls: ['./drawflow-renderer.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DrawflowRendererComponent implements AfterViewInit, OnChanges {

  @ViewChild('drawFlowDiv', {read: ElementRef, static: true})
  drawFlowDiv!: ElementRef;

  drawflowManager: DrawflowManager;
  @Input() disabled: boolean = false;
  @Input() workflow: Workflow = new Workflow();
  
  constructor(
    private injector: Injector,
    private applicationRef: ApplicationRef,
    private componentFactoryResolver: ComponentFactoryResolver) {
      this.drawflowManager = new DrawflowManager(injector, applicationRef, componentFactoryResolver);
  }

  ngOnChanges(){
    if(this.workflow) {
      this.drawflowManager.setWorkflow(this.workflow, this.disabled);
    }
  }

  ngAfterViewInit(){this.drawflowManager.init(this.drawFlowDiv.nativeElement);}
  
  zoomIn() {this.drawflowManager.zoomIn();}
  zoomOut() {this.drawflowManager.zoomOut();}
  onDrop(event: any){this.drawflowManager.drop(event);}
  export(): any {return this.drawflowManager.export();}

}
