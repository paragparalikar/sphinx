import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit, DynamicNodeComponent {

  constructor() { }

  ngOnInit(): void {
  }

  getData(){
    return undefined;
  }

  setData(data: any){ }

}
