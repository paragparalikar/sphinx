import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder } from 'formiojs';
import { MessageService } from 'primeng/api';
import { Alert } from 'src/app/shared/alert';
import { Form } from '../form.model';
import { FormService } from '../form.service';

@Component({
  selector: 'app-form-editor',
  templateUrl: './form-editor.component.html',
  styleUrls: ['./form-editor.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class FormEditorComponent implements OnInit {

  @ViewChild("formBuilderElement", {read: ElementRef, static: true})
  private formBuilderElement?: ElementRef;

  form: Form = {};
  formBuilder?: FormBuilder;
  title: string = 'Create New Form';
    
  constructor(
    private formService: FormService, 
    private activatedRoute: ActivatedRoute,
    private messageSerivce: MessageService) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.id){
          this.formService.findById(params.id).subscribe(form => {
            this.form = form;
            this.title = `Edit Form ${form.name}`;
            this.formBuilder!.setForm(this.form);
          });
        } else {
          this.form = {};
          this.title = "Create New Form";
        }
      }
    );

    this.formBuilder = new FormBuilder(this.formBuilderElement!.nativeElement, this.form, {
      noDefaultSubmitButton: true,
      builder: {
        premium: false,
        basic: {
          components: {
            password: false,
            button: false,
          },
        },
        advanced: {
          components: {
            signature: false,
          },
        }
      }
    });
  }

  save(){
    if(this.form.name && this.form.components && 0 < this.form.components.length){
      this.formService.save(this.form).subscribe(
        response => this.messageSerivce.add({
          severity: "success",
          summary: "Saved",
          detail: `Form "${this.form.name}" has been saved successfully`
        })
      );
    }
  }
}
