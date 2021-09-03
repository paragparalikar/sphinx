import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkflowListComponent } from './workflow-list/workflow-list.component';
import { WorkflowEditorComponent } from './workflow-editor/workflow-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { RequestComponent } from './workflow-editor/nodes/request/request.component';
import { EmailComponent } from './workflow-editor/nodes/email/email.component';
import { ApprovalComponent } from './workflow-editor/nodes/approval/approval.component';
import { LdapComponent } from './workflow-editor/nodes/ldap/ldap.component';
import { FormsModule } from '@angular/forms';
import { TransformerComponent } from './workflow-editor/nodes/transformer/transformer.component';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { PasswordModule } from 'primeng/password';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { ToolbarModule } from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { WorkflowNameValidatorDirective } from './workflow-name-validator.directive';

const routes: Routes = [
  {path: '', component: WorkflowListComponent},
  {path: 'editor', component: WorkflowEditorComponent}
];
@NgModule({
  declarations: [WorkflowListComponent, WorkflowEditorComponent, RequestComponent, EmailComponent, ApprovalComponent, LdapComponent, TransformerComponent, WorkflowNameValidatorDirective],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    DropdownModule,
    PasswordModule,
    InputTextModule,
    InputTextareaModule,
    TableModule,
    ButtonModule,
    ToolbarModule,
    ToastModule,
    InputTextModule,
    ConfirmPopupModule,
    RouterModule.forChild(routes)
  ]
})
export class WorkflowsModule { }
