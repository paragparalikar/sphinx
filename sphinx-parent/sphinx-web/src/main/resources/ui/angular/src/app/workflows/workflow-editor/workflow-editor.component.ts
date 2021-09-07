import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageService } from 'primeng/api';
import { NavigationService } from 'src/app/shared/navigation.service';
import nodes from "src/assets/workflow-nodes.json";
import { DrawflowRendererComponent } from '../../shared/drawflow/drawflow-renderer/drawflow-renderer.component';
import { Workflow } from '../workflow.model';
import { WorkflowService } from '../workflow.service';

@Component({
  selector: 'app-workflow-editor',
  templateUrl: './workflow-editor.component.html',
  styleUrls: ['./workflow-editor.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class WorkflowEditorComponent implements OnInit {

  @ViewChild(DrawflowRendererComponent)
  renderer!: DrawflowRendererComponent;
 
  nodeItems = nodes;
  workflow = new Workflow();
  
  constructor(
    private activatedRoute: ActivatedRoute,
    private workflowService: WorkflowService,
    private messageService: MessageService,
    private navigationService: NavigationService) {}

  ngOnInit(){
    this.activatedRoute.queryParams.subscribe(
      params => {
        if(params.id){
          this.workflowService.findById(params.id).subscribe(
            workflow => this.workflow = workflow
          );
        }
      }
    );
  }

  drag(event){
    event.dataTransfer.setData('node', event.target.getAttribute('data-node'));
  }

  isValid(): boolean{
    const forms: HTMLCollectionOf<HTMLFormElement> = document.getElementsByTagName('form');
    if(forms.length == 1) return false;
    for(let index = 0; index < forms.length; index++){
      const form: HTMLFormElement | null = forms.item(index);
      if(form && form.classList.contains('ng-invalid')) return false;
    }
    return true;
  }

  submit(){
    if(this.isValid()){
      this.workflow.data = this.renderer.export().drawflow.Home.data;
      this.workflowService.save(this.workflow).subscribe(
        response => {
          this.messageService.add({
            severity: "success",
            summary: "Saved",
            icon: "fa fa-check",
            detail: `Request for workflow "${this.workflow.name}" has been submitted successfully`
          });
          this.navigationService.navigate(['..'], {relativeTo: this.activatedRoute});
        },
        response => {
          if(response instanceof HttpErrorResponse){
            var message: string = '<ul>';
            response.error.messages.map(m => m.text).forEach(element => {
              message = message + `<li>${element}</li>`;
            });
            message = message + '</ul>';
            this.messageService.add({
              severity: "error",
              icon: "fa fa-exclamation-triangle",
              summary: "Validation Errors",
              sticky: true,
              closable: true,
              detail: message
            });
          }
        }
      );
    } else {
      this.messageService.add({
        severity: "error",
        summary: "Validation Errors",
        icon: "fa fa-exclamation-triangle",
        detail: `This page contains validation errors, please correct them before saving data`
      });
    }
  }

}
