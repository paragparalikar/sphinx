import { Component, OnInit } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Page } from 'src/app/shared/page.model';
import { AccessRequest } from '../access-request.model';
import { AccessRequestService } from '../access-request.service';

@Component({
  selector: 'app-access-request-list',
  templateUrl: './access-request-list.component.html',
  styleUrls: ['./access-request-list.component.css']
})
export class AccessRequestListComponent implements OnInit {

  page: Page<AccessRequest> = {
    content: [],
    totalElements: 0
  };
  loading: boolean = true;
  lazyLoadEvent?: LazyLoadEvent;

  constructor(
    private accessRequestService: AccessRequestService,
    private messageSerivce: MessageService, 
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
    this.lazyLoadEvent = event;
    this.accessRequestService.findAll(event).subscribe(
      page => {
        that.page = page;
        that.loading = false;
      }
    );
  }

  cancel(event: any, request: AccessRequest) {
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
        this.accessRequestService.deleteById(request.id!).subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Cancelled",
              detail: `Request "${request.id}" has been deleted successfully`
            });
          }
        );
      }
    });
  }
}
