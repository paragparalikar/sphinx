import { Component, OnInit } from '@angular/core';
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
  loading: boolean = true;
  lazyLoadEvent?: LazyLoadEvent;
  statuses = [
    {label: 'NEW', value: 'NEW'},
    {label: 'PENDING', value: 'PENDING'},
    {label: 'APPROVED', value: 'APPROVED'},
    {label: 'REJECTED', value: 'REJECTED'},
    {label: 'CANCELLED', value: 'CANCELLED'}
  ];

  constructor(
    private requestService: RequestService,
    private messageSerivce: MessageService, 
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
    this.lazyLoadEvent = event;
    this.requestService.findAll(event).subscribe(
      page => {
        that.page = page;
        that.loading = false;
      }
    );
  }

  cancel(event: any, request: Request) {
    this.confirmationService.confirm({
      message: `Are you sure you want to cancel request "${request.id}" ?`,
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