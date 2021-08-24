import { Component, OnDestroy, OnInit } from '@angular/core';
import { ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { Alert } from 'src/app/shared/alert';
import { Page } from 'src/app/shared/page.model';
import { Form } from '../form.model';
import { FormService } from '../form.service';

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit, OnDestroy {

  alerts: Alert[] = [];
  page: Page<Form> = {
    numberOfElements: 0,
    content: []
  };
  loading: boolean = true;
    
  constructor(
    private formService: FormService, 
    private confirmationService: ConfirmationService) { }

  ngOnDestroy(): void {

  }

  ngOnInit(): void {
    
  }

  load(event: LazyLoadEvent){
    const that = this;
    this.loading = true;
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
      key: String(form.id),
      icon: "pi pi-exclamation-triangle",
      acceptIcon: 'pi pi-trash',
      acceptLabel: "Delete",
      acceptButtonStyleClass: 'btn btn-danger',
      rejectLabel: "Cancel",
      rejectIcon: "pi pi-times",
      rejectButtonStyleClass: "btn btn-secondary",  
      target: event.target,
      closeOnEscape: true
    });
  }

}
