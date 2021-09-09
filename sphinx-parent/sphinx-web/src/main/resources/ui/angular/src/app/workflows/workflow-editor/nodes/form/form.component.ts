import { Component, OnInit } from '@angular/core';
import { DynamicNodeComponent } from '../dynamic-node-component';
import { FormVertex } from './form-vertex';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements DynamicNodeComponent {

  formVertext: FormVertex = new FormVertex();

  getData() {
    return this.formVertext;
  }

  setData(data: any) {
    this.formVertext = data as FormVertex;
  }

  setDisabled(value: boolean) {
    
  }


}
