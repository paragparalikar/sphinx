import { Component, OnChanges, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { TransformerVertex } from './transformer-vertex';

@Component({
  selector: 'app-transformer',
  templateUrl: './transformer.component.html',
  styleUrls: ['./transformer.component.css']
})
export class TransformerComponent implements DynamicNodeComponent {

  @ViewChild(NgForm, {read: NgForm, static: true})
  form!: NgForm;
  
  transformerVertex: TransformerVertex = {};

  getData(){
    return this.transformerVertex;
  }

  setData(data: any){
    this.transformerVertex = data as TransformerVertex;
  }

}
