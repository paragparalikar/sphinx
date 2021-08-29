import { Component, Input, OnInit } from '@angular/core';
import { NodeModel } from '@rxzu/angular';

@Component({
  selector: 'app-approval-node',
  templateUrl: './approval-node.component.html',
  styleUrls: ['./approval-node.component.css']
})
export class ApprovalNodeComponent implements OnInit {

  @Input()
  model!: NodeModel;

  constructor() { }

  ngOnInit(): void {
  }

}
