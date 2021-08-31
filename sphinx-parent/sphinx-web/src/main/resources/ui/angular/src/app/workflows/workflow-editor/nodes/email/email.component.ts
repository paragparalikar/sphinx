import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { EmailVertex } from './email-vertex';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements OnInit, DynamicNodeComponent {

  emailVertex: EmailVertex = {};
  recipients = [
    {name: "Requester", value: "requester"},
    {name: "Manager (Level 1)", value: "manager-1"},
    {name: "Manager (Level 2)", value: "manager-2"},
    {name: "Application Owner", value: "application-owner"}
  ];

  constructor() { }

  ngOnInit(): void {
  }

  getData(){
    return this.emailVertex;
  }

  setData(data: any){
    this.emailVertex = data as EmailVertex;
  }

}
