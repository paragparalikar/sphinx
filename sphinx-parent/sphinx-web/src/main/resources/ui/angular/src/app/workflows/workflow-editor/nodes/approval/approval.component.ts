import { Component, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { ApprovalVertex } from './approval-vertex';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements DynamicNodeComponent {

  disabled: boolean = false;
  approvalVertex: ApprovalVertex = new ApprovalVertex();
  
  @ViewChild(NgForm, {read: NgForm, static: true})
  form!: NgForm;

  getData(){
    return this.approvalVertex;
  }

  setData(data: any){
    this.approvalVertex = data as ApprovalVertex;
  }

  setDisabled(value: boolean) {
    this.disabled = value;
  }
}
