import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Page } from 'src/app/shared/page.model';
import { Request } from '../request.model';
import { RequestService } from '../request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  page: Page<Request> = {
    content: [],
    totalElements: 0
  };
  role: string = 'requester';
  loading: boolean = true;
  lazyLoadEvent?: LazyLoadEvent;
  statuses = [
    {label: 'NEW', value: 'NEW'},
    {label: 'PENDING', value: 'PENDING'},
    {label: 'APPROVED', value: 'APPROVED'},
    {label: 'REJECTED', value: 'REJECTED'},
    {label: 'CANCELLED', value: 'CANCELLED'}
  ];
  types = [
    {label: 'ACCESS', value: 'ACCESS'},
    {label: 'FORM', value: 'FORM'},
    {label: 'WORKFLOW', value: 'WORKFLOW'},
  ];


  constructor(
    private route: ActivatedRoute,
    private requestService: RequestService,
    private messageSerivce: MessageService, 
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.role = params.role ? params.role : 'requester';
        if(this.lazyLoadEvent){
          this.load(this.lazyLoadEvent);
        }
      }
    );
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
    this.lazyLoadEvent = event;
    this.requestService.findAll(this.role, event).subscribe(
      page => {
        that.page = page;
        that.loading = false;
      }
    );
  }

  approve(event: any, request: Request){
    this.confirmationService.confirm({
      message: `Are you sure you want to approve request "${request.name}" ?`,
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
        this.requestService.decide(request.id!, 'output_1').subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Approved",
              icon: "fa fa-check",
              detail: `Request "${request.name}" has been approved successfully`
            });
          }
        );
      }
    });
  }

  reject(event: any, request: Request){
    this.confirmationService.confirm({
      message: `Are you sure you want to reject request "${request.name}" ?`,
      icon: "fa fa-question-circle",
      acceptLabel: "Yes",
      acceptButtonStyleClass: 'btn btn-danger',
      rejectLabel: "No",
      rejectButtonStyleClass: "btn btn-plain",  
      target: event.target,
      closeOnEscape: true,
      accept: () => {
        this.requestService.decide(request.id!, 'output_2').subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Rejected",
              icon: "fa fa-check",
              detail: `Request "${request.name}" has been rejected successfully`
            });
          }
        );
      }
    });
  }

  cancel(event: any, request: Request) {
    this.confirmationService.confirm({
      message: `Are you sure you want to cancel request "${request.name}" ?`,
      icon: "pi pi-exclamation-triangle",
      acceptIcon: 'pi pi-trash',
      acceptLabel: "Yes",
      acceptButtonStyleClass: 'btn btn-danger',
      rejectLabel: "No",
      rejectIcon: "pi pi-times",
      rejectButtonStyleClass: "btn btn-plain",  
      target: event.target,
      closeOnEscape: true,
      accept: () => {
        this.requestService.deleteById(request.id!).subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Cancelled",
              icon: "fa fa-check",
              detail: `Request "${request.id}" has been cancelled successfully`
            });
          }
        );
      }
    });
  }
}
