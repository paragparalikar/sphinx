import { Component, Input, OnInit } from '@angular/core';
import { NodeModel } from '@rxzu/angular';

@Component({
  selector: 'app-email-node',
  templateUrl: './email-node.component.html',
  styleUrls: ['./email-node.component.css']
})
export class EmailNodeComponent implements OnInit {

  @Input()
  model!: NodeModel;

  constructor() { }

  ngOnInit(): void {
  }

}
