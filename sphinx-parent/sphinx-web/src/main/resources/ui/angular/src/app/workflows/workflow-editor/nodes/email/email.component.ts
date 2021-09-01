import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { EmailVertex } from './email-vertex';

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.css']
})
export class EmailComponent implements DynamicNodeComponent {

  emailVertex: EmailVertex = {};

  @ViewChild(NgForm, {read: NgForm, static: true})
  form!: NgForm;
  
  recipients = [
    {name: "Requester", value: "requester"},
    {name: "Manager (Level 1)", value: "manager-1"},
    {name: "Manager (Level 2)", value: "manager-2"},
    {name: "Application Owner", value: "application-owner"}
  ];

  getData(){
    return this.emailVertex;
  }

  setData(data: any){
    this.emailVertex = data as EmailVertex;
  }
}
