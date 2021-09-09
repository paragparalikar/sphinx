import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Form } from 'src/app/forms/form.model';
import { FormService } from 'src/app/forms/form.service';
import { Request } from '../request.model';
import { RequestService } from '../request.service';
import { Formio } from 'formiojs';
import { NavigationService } from 'src/app/shared/navigation.service';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-form-request-editor',
  templateUrl: './form-request-editor.component.html',
  styleUrls: ['./form-request-editor.component.css']
})
export class FormRequestEditorComponent implements OnInit {

  @ViewChild("formRendererElement", {read: ElementRef, static: true})
  private formRendererElement?: ElementRef;

  form?: Form;
  formioForm?: any;
  request: Request = {};
  formioOptions = {
    noAlerts: true,
    readOnly: false,
    highlightErrors: false
  };
  
  constructor(
    private route: ActivatedRoute,
    private formService: FormService,
    private messageService: MessageService,
    private navigationService: NavigationService,
    private requestService: RequestService,
    private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        if(params.id){
          this.load(params.id);
        }
      }
    );
  }

  load(id: number){
    this.requestService.findById(id).subscribe(
      request => {
        this.request = request;
        this.request.payload.components = JSON.parse(request.payload.components);
        this.render(this.request.payload);
      }
    );
  }

  render(form: Form){
    this.form = form;
    this.request.targetId = form.id;
    this.request.targetName = form.name;
    this.formioOptions.readOnly = this.request.id != undefined;
    Formio.createForm(this.formRendererElement!.nativeElement, form, this.formioOptions).then(
      form => this.formioForm = form
    );
  }

  back(){
    this.navigationService.navigate(['..'], {relativeTo: this.route});
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