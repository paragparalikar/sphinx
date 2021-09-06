import { Component, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { LdapVertex } from './ldap-vertex';

@Component({
  selector: 'app-ldap',
  templateUrl: './ldap.component.html',
  styleUrls: ['./ldap.component.css']
})
export class LdapComponent implements DynamicNodeComponent {

  disabled: boolean = false;
  ldapVertex: LdapVertex = new LdapVertex();

  @ViewChild(NgForm, {read: NgForm, static: true})
  form!: NgForm;

  getData(){
    return this.ldapVertex;
  }
   
  setData(data: any){
    this.ldapVertex = data as LdapVertex;
  }

  setDisabled(value: boolean) {
    this.disabled = value;
  }
}
