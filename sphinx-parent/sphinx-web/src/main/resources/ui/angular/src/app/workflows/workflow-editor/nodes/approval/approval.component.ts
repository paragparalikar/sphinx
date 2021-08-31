import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { ApprovalVertex } from './approval-vertex';

@Component({
  selector: 'app-approval',
  templateUrl: './approval.component.html',
  styleUrls: ['./approval.component.css']
})
export class ApprovalComponent implements OnInit, DynamicNodeComponent {

  approvalVertex: ApprovalVertex = {};

  constructor() { }

  ngOnInit(): void {
  }

}
