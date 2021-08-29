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
    { type:'request', name: 'Request Received', icon:'fa fa-arrow-right' },
    { type:'email', name: 'Email', icon: 'fa fa-envelope'},
    { type:'approval', name: 'Approval', icon: 'fa fa-check' },
    { type:'ldap', name: 'LDAP', icon: 'fa fa-users' }
  ];
  @ViewChild(RxZuDiagramComponent, { static: true })
  diagram?: RxZuDiagramComponent;

  constructor() {
    this.diagramModel = new DiagramModel();
  }

  ngAfterViewInit() {
    this.diagram?.zoomToFit();
  }

  createNode(type: string) {
    const nodeData = {
      type: type
    };
    const node = new NodeModel();
    node.setExtras(nodeData);
    return node;
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
      const node = this.createNode(nodeType);
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
          this.diagramModel.addNode(node);
        }
      }
    }
  }
}
