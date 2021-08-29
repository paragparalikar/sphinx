import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { DiagramModel, NodeModel, PortModel, RxZuDiagramComponent } from '@rxzu/angular';

@Component({
  selector: 'app-workflow-editor',
  templateUrl: './workflow-editor.component.html',
  styleUrls: ['./workflow-editor.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class WorkflowEditorComponent {
  diagramModel: DiagramModel;
  nodesLibrary = [
    { type:'request', name: 'Request Received', icon:'fa fa-arrow-right', inputs: 0, outputs: 1, height: 100, width: 150 },
    { type:'email', name: 'Email', icon: 'fa fa-envelope', inputs: 1, outputs: 1, height: 100, width: 150 },
    { type:'approval', name: 'Approval', icon: 'fa fa-check', inputs: 1, outputs: 2, height: 100, width: 150 },
    { type:'ldap', name: 'LDAP', icon: 'fa fa-users', inputs: 1, outputs: 1, height: 100, width: 150 }
  ];
  @ViewChild(RxZuDiagramComponent, { static: true })
  diagram?: RxZuDiagramComponent;

  constructor() {
    this.diagramModel = new DiagramModel();
  }

  ngAfterViewInit() {
    this.diagram?.zoomToFit();
  }

  createNode(nodeDef) {
    const nodeData = {
      type: nodeDef.type,
      data: {},
      status: undefined
    };
    const node = new NodeModel();
    node.setExtras(nodeData);
    node.setHeight(nodeDef.height);
    node.setWidth(nodeDef.width);
    return node;
  }

  createPorts(nodeDef: any, node: NodeModel){
    if(0 < nodeDef.inputs){
      const step = nodeDef.height / (nodeDef.inputs + 1);
      for(var index = 1; index <= nodeDef!.inputs; index++){
        const y = node.getCoords().x +  index * step;
        const port = new PortModel();
        port.updateCoords({
          x: 0,
          y: y,
          width: 5,
          height: 5
        });
        node.addPort(port);
      }
    }
  }

  onBlockDrag(e: DragEvent) {
    const type = (e.target as HTMLElement).getAttribute('data-type');
    if (e.dataTransfer && type) {
      e.dataTransfer.setData('type', type);
    }
  }

  onBlockDropped(e: DragEvent): void | undefined {
    if (e.dataTransfer) {
      const nodeType = e.dataTransfer.getData('type');
      const nodeDef = this.nodesLibrary.find(n => n.type == nodeType);
      const node = this.createNode(nodeDef);
      const canvasManager = this.diagram?.diagramEngine.getCanvasManager();
      if (canvasManager) {
        const droppedPoint = canvasManager.getZoomAwareRelativePoint(e);
        const width = node?.getWidth() ?? 1;
        const height = node?.getHeight() ?? 1;
        const coords = {
          x: droppedPoint.x - width / 2,
          y: droppedPoint.y - height / 2,
        };
        if (node) {
          node.setCoords(coords);
          this.createPorts(nodeDef, node);
          this.diagramModel.addNode(node);
        }
      }
    }
  }
}
