import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { WorkflowVertex } from './workflow-vertex';

@Component({
  selector: 'app-workflow',
  templateUrl: './workflow.component.html',
  styleUrls: ['./workflow.component.css']
})
export class WorkflowComponent implements DynamicNodeComponent {

  workflowVertex: WorkflowVertex = new WorkflowVertex();

  getData() {
    return this.workflowVertex;
  }
  setData(data: any) {
    this.workflowVertex = data as WorkflowVertex;
  }
  setDisabled(value: boolean) {
    
  }

}
