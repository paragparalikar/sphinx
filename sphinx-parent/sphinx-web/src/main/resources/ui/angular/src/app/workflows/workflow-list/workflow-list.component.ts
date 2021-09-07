import { Component, OnInit } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Page } from 'src/app/shared/page.model';
import { Workflow } from '../workflow.model';
import { WorkflowService } from '../workflow.service';
import { Request } from 'src/app/requests/request.model';
import { RequestService } from 'src/app/requests/request.service';

@Component({
  selector: 'app-workflow-list',
  templateUrl: './workflow-list.component.html',
  styleUrls: ['./workflow-list.component.css']
})
export class WorkflowListComponent implements OnInit {

  page: Page<Workflow> = {
    totalElements: 0,
    content: []
  };
  loading: boolean = true;
  lazyLoadEvent?: LazyLoadEvent;
  request: Request = {
    type: 'WORKFLOW'
  };

  constructor(
    private workflowService: WorkflowService,
    private messageSerivce: MessageService, 
    private requestService: RequestService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
    this.lazyLoadEvent = event;
    this.workflowService.findAll(event).subscribe(
      page => {
        that.page = page;
        that.loading = false;
      }
    );
  }

  delete(event: any, workflow: Workflow) {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete workflow "${workflow.name}" ?`,
      icon: "pi pi-exclamation-triangle",
      acceptIcon: 'pi pi-trash',
      acceptLabel: "Delete",
      acceptButtonStyleClass: 'btn btn-danger',
      rejectLabel: "Cancel",
      rejectIcon: "pi pi-times",
      rejectButtonStyleClass: "btn btn-plain",  
      target: event.target,
      closeOnEscape: true,
      accept: () => {
        this.request.targetId = workflow.id;
        this.requestService.save(this.request).subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Deleted",
              detail: `Workflow "${workflow.name}" has been deleted successfully`
            });
          }
        );
      }
    });
  }


}
