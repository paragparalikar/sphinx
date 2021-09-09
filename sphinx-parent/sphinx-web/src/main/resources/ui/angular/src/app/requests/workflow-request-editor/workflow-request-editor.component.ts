import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {css} from "lit-element"; 
import { Workflow } from '../../workflows/workflow.model';
import { RequestService } from '../request.service';
import { Request } from 'src/app/requests/request.model';

@Component({
  selector: 'app-workflow-request-editor',
  templateUrl: './workflow-request-editor.component.html',
  styleUrls: ['./workflow-request-editor.component.css']
})
export class WorkflowRequestEditorComponent implements OnInit {

  request?: Request;
  workflow = new Workflow();
  role: string = 'requester';
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private requestService: RequestService) {}

  ngOnInit(){
    this.activatedRoute.queryParams.subscribe(
      params => {
        this.role = params.role ? params.role : 'requester';
        if(params.id){
          this.requestService.findById(params.id).subscribe(
            response => {
              this.request = response;
              this.workflow = response.payload;
            }
          );
        }
      }
    );
  }
}
