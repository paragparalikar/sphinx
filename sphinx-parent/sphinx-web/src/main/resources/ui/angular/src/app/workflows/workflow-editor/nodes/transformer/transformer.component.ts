import { Component, OnInit } from '@angular/core';
import { TransformerVertex } from './transformer-vertex';

@Component({
  selector: 'app-transformer',
  templateUrl: './transformer.component.html',
  styleUrls: ['./transformer.component.css']
})
export class TransformerComponent implements OnInit {

  transformerVertex: TransformerVertex = {};

  constructor() { }

  ngOnInit(): void {
  }

}
