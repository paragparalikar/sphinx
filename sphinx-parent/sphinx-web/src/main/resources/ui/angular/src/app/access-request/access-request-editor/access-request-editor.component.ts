import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Form } from 'src/app/forms/form.model';
import { FormService } from 'src/app/forms/form.service';
import { AccessRequest } from '../access-request.model';
import { AccessRequestService } from '../access-request.service';
import { Formio } from 'formiojs';
import { NavigationService } from 'src/app/shared/navigation.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-access-request-editor',
  templateUrl: './access-request-editor.component.html',
  styleUrls: ['./access-request-editor.component.css']
})
export class AccessRequestEditorComponent implements OnInit {

  @ViewChild("formRendererElement", {read: ElementRef, static: true})
  private formRendererElement?: ElementRef;

  form?: Form;
  formioForm?: any;
  title: string = "New Request";
  request: AccessRequest = {};
  formSuggestions: Form[] = [];
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
    private accessRequestService: AccessRequestService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        if(params.id){
          this.accessRequestService.findById(params.id).subscribe(
            accessRequest => {
              this.request = accessRequest;
              this.formService.findById(this.request.formId!).subscribe(
                form => this.render(form)
              );
            }
          );
        }
      }
    );
  }

  suggestForms(event: any){
    this.formService.findSuggestions(event.query).subscribe(
      forms => this.formSuggestions = forms
    );
  }

  onFormSelect(event: any){
    this.formService.findById(event.id).subscribe(
      form => this.render(form)
    );
  }

  render(form: Form){
    this.form = form;
    this.title = form.name!;
    this.request.formId = form.id;
    this.request.formName = form.name;
    this.formioOptions.readOnly = this.request.id != undefined;
    Formio.createForm(this.formRendererElement!.nativeElement, form, this.formioOptions).then(
      form => {
        this.formioForm = form;
        this.formioForm.submission = {
          data: this.request.payload
        };
      }
    );
  }

  back(){
    this.navigationService.navigate(['..'], {relativeTo: this.route});
  }

  submit(requestForm){
    Object.keys(requestForm.controls).forEach(key => {
      requestForm.controls[key].markAsTouched();
    });
    this.formioForm.submit().then( 
      (submission: { data: any; }) => {
        if(requestForm.touched && requestForm.valid){
          this.request.payload = JSON.stringify(submission.data);
        this.accessRequestService.save(this.request).subscribe(
          response => {
            this.messageService.add({
              severity: "success",
              summary: "Saved",
              detail: `Request has been saved successfully`
            });
            this.back();
          });
        }
      }
    );
  }
}
