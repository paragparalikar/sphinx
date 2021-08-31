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

  constructor() { }

  ngOnInit(): void {
  }

}
