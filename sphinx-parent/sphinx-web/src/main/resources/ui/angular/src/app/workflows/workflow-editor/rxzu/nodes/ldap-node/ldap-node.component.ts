import { Component, Input, OnInit } from '@angular/core';
import { NodeModel } from '@rxzu/angular';

@Component({
  selector: 'app-ldap-node',
  templateUrl: './ldap-node.component.html',
  styleUrls: ['./ldap-node.component.css']
})
export class LdapNodeComponent implements OnInit {

  @Input()
  model!: NodeModel;

  constructor() { }

  ngOnInit(): void {
  }

}
