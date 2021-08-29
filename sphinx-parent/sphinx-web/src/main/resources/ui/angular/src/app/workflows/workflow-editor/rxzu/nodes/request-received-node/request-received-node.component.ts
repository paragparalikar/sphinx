import { Component, Input, OnInit } from '@angular/core';
import { NodeModel } from '@rxzu/angular';

@Component({
  selector: 'app-request-received-node',
  templateUrl: './request-received-node.component.html',
  styleUrls: ['./request-received-node.component.css']
})
export class RequestReceivedNodeComponent implements OnInit {

  @Input()
  model!: NodeModel;

  constructor() { }

  ngOnInit(): void {
  }

}
