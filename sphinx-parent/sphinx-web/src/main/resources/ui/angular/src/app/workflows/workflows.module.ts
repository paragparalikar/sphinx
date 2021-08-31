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

const routes: Routes = [
  {path: '', component: WorkflowListComponent},
  {path: 'editor', component: WorkflowEditorComponent}
];
@NgModule({
  declarations: [WorkflowListComponent, WorkflowEditorComponent, RequestComponent, EmailComponent, ApprovalComponent, LdapComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    FormsModule,
    RouterModule.forChild(routes)
  ]
})
export class WorkflowsModule { }
