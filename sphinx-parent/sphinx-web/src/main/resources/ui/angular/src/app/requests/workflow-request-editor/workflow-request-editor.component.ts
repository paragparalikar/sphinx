import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {css} from "lit-element"; 
import { Workflow } from '../../workflows/workflow.model';
import { RequestService } from '../request.service';
import { Request } from 'src/app/requests/request.model';
import { ConfirmationService, MessageService } from 'primeng/api';

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
    private messageService: MessageService,
    private activatedRoute: ActivatedRoute,
    private requestService: RequestService,
    private confirmationService: ConfirmationService) {}

  ngOnInit(){
    this.activatedRoute.queryParams.subscribe(
      params => {
        this.role = params.role ? params.role : 'requester';
        if(params.id){
          this.load(params.id);
        }
      }
    );
  }

  load(id: number){
    this.requestService.findById(id).subscribe(
      response => {
        this.request = response;
        this.workflow = response.payload;
      }
    );
  }

  approve(event: any){
    this.confirmationService.confirm({
      message: `Are you sure you want to approve request "${this.request!.name}" ?`,
      icon: "fa fa-question-circle",
      acceptIcon: 'fa fa-check',
      acceptLabel: "Yes",
      acceptButtonStyleClass: 'btn btn-success',
      rejectLabel: "No",
      rejectIcon: "fa fa-times",
      rejectButtonStyleClass: "btn btn-plain",  
      target: event.target,
      closeOnEscape: true,
      accept: () => {
        this.requestService.decide(this.request!.id!, 'output_1').subscribe(
          response => {
            this.load(this.request!.id!);
            this.messageService.add({
              severity: "success",
              summary: "Approved",
              icon: "fa fa-check",
              detail: `Request "${this.request!.name}" has been approved successfully`
            });
          }
        );
      }
    });
  }

  reject(event: any){
    this.confirmationService.confirm({
      message: `Are you sure you want to reject request "${this.request!.name}" ?`,
      icon: "fa fa-question-circle",
      acceptLabel: "Yes",
      acceptButtonStyleClass: 'btn btn-danger',
      rejectLabel: "No",
      rejectButtonStyleClass: "btn btn-plain",  
      target: event.target,
      closeOnEscape: true,
      accept: () => {
        this.requestService.decide(this.request!.id!, 'output_2').subscribe(
          response => {
            this.load(this.request!.id!);
            this.messageService.add({
              severity: "success",
              summary: "Rejected",
              icon: "fa fa-check",
              detail: `Request "${this.request!.name}" has been rejected successfully`
            });
          }
        );
      }
    });
  }
}
