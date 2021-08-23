import { Component, ElementRef, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AlertsPosition } from '@formio/angular/types/alerts-position';
import { FormBuilder } from 'formiojs';
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

  alerts: Alert[] = [];
  form: Form = {};
  formBuilder?: FormBuilder;
  title: string = 'Create New Form';
    
  constructor(private formService: FormService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {

    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.id){
          this.formService.findById(params.id).subscribe(form => this.form = form);
        } else {
          this.form = {};
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
        response => this.alerts.push({
          type: 'alert-success',
          text: "Form has been saved successfully"
        })
      );
    }
  }
}
