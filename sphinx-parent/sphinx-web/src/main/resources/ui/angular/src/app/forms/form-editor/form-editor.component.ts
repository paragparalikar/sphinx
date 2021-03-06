import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from 'formiojs';
import { MessageService } from 'primeng/api';
import { Form } from '../form.model';
import { FormService } from '../form.service';
import * as formBuilderOptions from 'src/assets/form-builder-options.json';
import { NavigationService } from 'src/app/shared/navigation.service';
import { Workflow } from 'src/app/workflows/workflow.model';
import { WorkflowService } from 'src/app/workflows/workflow.service';
import { NgForm } from '@angular/forms';
import { RequestService } from 'src/app/requests/request.service';
import { Request } from 'src/app/requests/request.model';

@Component({
  selector: 'app-form-editor',
  templateUrl: './form-editor.component.html',
  styleUrls: ['./form-editor.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FormEditorComponent implements OnInit {

  @ViewChild("formBuilderElement", {read: ElementRef, static: true})
  private formBuilderElement?: ElementRef;

  @ViewChild("formBuilderForm", {read: NgForm, static: true})
  private formBuilderForm?: NgForm;

  form: Form = {};
  workflow?: Workflow;
  workflowSuggestions: Workflow[] = [];
  formBuilder?: FormBuilder;

  constructor(
    private formService: FormService, 
    private requestService: RequestService,
    private activatedRoute: ActivatedRoute,
    private workflowService: WorkflowService,
    private navigationService: NavigationService,
    private messageSerivce: MessageService) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.id){
          this.formService.findById(params.id).subscribe(form => {
            this.form = form;
            this.formBuilder!.setForm(this.form);
          });
        } else {
          this.form = {};
        }
      }
    );

    this.formBuilder = new FormBuilder(this.formBuilderElement!.nativeElement, 
      this.form, formBuilderOptions);
  }

  suggestWorkflows(event: any){
    this.workflowService.findSuggestions(event.query).subscribe(
      workflows => this.workflowSuggestions = workflows
    );
  }

  save(){
    if(this.formBuilderForm?.invalid){
      this.messageSerivce.add({
        severity: "error",
        summary: "Validation Errors",
        icon: "fa fa-exclamation-triangle",
        detail: "Please fix validation errors before saving data"
      });
    } else if(!this.form.components || 0 == this.form.components.length){
      this.messageSerivce.add({
        severity: "error",
        summary: "Validation Errors",
        icon: "fa fa-exclamation-triangle",
        detail: "There are no components in the form to save"
      });
    } else {
      const clone = Object.assign({}, this.form);
      clone.components = JSON.stringify(this.form.components);
      const request: Request = {
        type: 'FORM',
        name: this.form.name,
        targetId: this.form.id,
        payload: clone
      };
      this.requestService.save(request).subscribe(
        response => {
          this.messageSerivce.add({
            severity: "success",
            summary: "Saved",
            icon: "fa fa-check",
            detail: `Request for form "${this.form.name}" has been submitted successfully`
          });
          this.navigationService.navigateByUrl('/requests');
        }
      );
    }
  }
}
