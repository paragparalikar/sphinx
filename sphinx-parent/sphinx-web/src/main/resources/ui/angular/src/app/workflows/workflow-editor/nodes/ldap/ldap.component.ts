import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { LdapVertex } from './ldap-vertex';

@Component({
  selector: 'app-ldap',
  templateUrl: './ldap.component.html',
  styleUrls: ['./ldap.component.css']
})
export class LdapComponent implements OnInit, DynamicNodeComponent {

  ldapVertex: LdapVertex = {};

  constructor() { }

  ngOnInit(): void {
  }

  getData(){
    return this.ldapVertex;
  }
   
  setData(data: any){
    this.ldapVertex = data as LdapVertex;
  }

}
