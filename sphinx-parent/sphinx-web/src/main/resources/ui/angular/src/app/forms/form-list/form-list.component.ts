import { Component, OnDestroy, OnInit } from '@angular/core';
import { ConfirmationService, LazyLoadEvent, MessageService } from 'primeng/api';
import { Page } from 'src/app/shared/page.model';
import { Form } from '../form.model';
import { FormService } from '../form.service';

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit, OnDestroy {

  page: Page<Form> = {
    totalElements: 0,
    content: []
  };
  loading: boolean = true;
  lazyLoadEvent?: LazyLoadEvent;
    
  constructor(
    private formService: FormService,
    private messageSerivce: MessageService, 
    private confirmationService: ConfirmationService) { }

  ngOnDestroy(): void {

  }

  ngOnInit(): void {
    
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
    this.lazyLoadEvent = event;
    this.formService.findAll(event).subscribe(
      page => {
        that.page = page;
        that.loading = false;
      }
    );
  }

  delete(event: any, form: Form) {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete form "${form.name}" ?`,
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
        this.formService.deleteById(form.id!).subscribe(
          response => {
            this.load(this.lazyLoadEvent!);
            this.messageSerivce.add({
              severity: "success",
              summary: "Deleted",
              icon: "fa fa-check",
              detail: `Form "${form.name}" has been deleted successfully`
            });
          }
        );
      }
    });
  }

}
