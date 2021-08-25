import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Form } from 'src/app/forms/form.model';
import { FormService } from 'src/app/forms/form.service';
import { User } from 'src/app/user/user.model';
import { UserService } from 'src/app/user/user.service';
import { AccessRequest } from '../access-request.model';
import { AccessRequestService } from '../access-request.service';
import { Formio } from 'formiojs';
import { NavigationService } from 'src/app/shared/navigation.service';


@Component({
  selector: 'app-access-request-editor',
  templateUrl: './access-request-editor.component.html',
  styleUrls: ['./access-request-editor.component.css']
})
export class AccessRequestEditorComponent implements OnInit {

  @ViewChild("formRendererElement", {read: ElementRef, static: true})
  private formRendererElement?: ElementRef;

  form?: any;
  title: string = "New Request";
  request: AccessRequest = {};
  userSuggestions: User[] = [];
  formSuggestions: Form[] = [];
  
  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private formService: FormService,
    private navigationService: NavigationService,
    private accessRequestService: AccessRequestService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        if(params.id){
          this.accessRequestService.findById(params.id).subscribe(
            accessRequest => {
              this.request = accessRequest;
              this.render();
            }
          );
        }
      }
    );
  }

  suggestUsers(event: any){
    this.userService.findSuggestions(event.query).subscribe(
      users => this.userSuggestions = users
    );
  }

  suggestForms(event: any){
    this.formService.findSuggestions(event.query).subscribe(
      forms => this.formSuggestions = forms
    );
  }

  onFormSelect(event: any){
    this.formService.findById(event.id).subscribe(
      form => {
        this.request.form = form;
        this.render();
      }
    );
  }

  render(){
    Formio.createForm(this.formRendererElement!.nativeElement, this.request!.form!).then(
      form => {
        this.form = form;
      }
    );
  }

  back(){
    this.navigationService.back();
  }

  submit(){
    console.log(this.form._data);
    this.request.payload = JSON.stringify(this.form._data);
  }
}
