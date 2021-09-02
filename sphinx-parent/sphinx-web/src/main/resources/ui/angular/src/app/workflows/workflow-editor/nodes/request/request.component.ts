import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { RequestVertex } from './request-vertex';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit, DynamicNodeComponent {

  requestVertex: RequestVertex = new RequestVertex();

  constructor() { }

  ngOnInit(): void {
  }

  getData(){
    return this.requestVertex;
  }

  setData(data: any){ 
    this.requestVertex = data as RequestVertex;
  }

}
