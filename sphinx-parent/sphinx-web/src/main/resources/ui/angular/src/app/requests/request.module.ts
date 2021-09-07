import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestListComponent } from './request-list/request-list.component';
import { AccessRequestEditorComponent } from './access-request-editor/access-request-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { FormRequestEditorComponent } from './form-request-editor/form-request-editor.component';
import { WorkflowRequestEditorComponent } from './workflow-request-editor/workflow-request-editor.component';
import { WorkflowsModule } from '../workflows/workflows.module';
import { DrawflowModule } from '../shared/drawflow/drawflow.module';

const routes: Routes = [
  {path: '', component: RequestListComponent},
  {path: 'form-request-editor', component: FormRequestEditorComponent},
  {path: 'access-request-editor', component: AccessRequestEditorComponent},
  {path: 'workflow-request-editor', component: WorkflowRequestEditorComponent}
];

@NgModule({
  declarations: [
    RequestListComponent,
    AccessRequestEditorComponent,
    FormRequestEditorComponent,
    WorkflowRequestEditorComponent
  ],
  imports: [
    CommonModule,
    TableModule,
    ButtonModule,
    ToolbarModule,
    ToastModule,
    CardModule,
    FormsModule,
    InputTextModule,
    ConfirmPopupModule,
    AutoCompleteModule,
    DropdownModule,
    DrawflowModule,
    RouterModule.forChild(routes)
  ]
})
export class RequestModule { }
