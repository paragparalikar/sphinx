import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Form } from 'src/app/forms/form.model';
import { FormService } from 'src/app/forms/form.service';
import { Request } from '../request.model';
import { RequestService } from '../request.service';
import { Formio } from 'formiojs';
import { NavigationService } from 'src/app/shared/navigation.service';
import { MessageService } from 'primeng/api';

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
    private requestService: RequestService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        if(params.id){
          this.requestService.findById(params.id).subscribe(
            accessRequest => {
              this.request = accessRequest;
              this.formService.findById(this.request.targetId!).subscribe(
                form => this.render(form)
              );
            }
          );
        }
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
}