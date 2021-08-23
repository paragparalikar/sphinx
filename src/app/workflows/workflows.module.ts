import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkflowListComponent } from './workflow-list/workflow-list.component';
import { WorkflowEditorComponent } from './workflow-editor/workflow-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DrawFlowDirective } from './draw-flow.directive';

const routes: Routes = [
  {path: '', component: WorkflowListComponent},
  {path: 'editor', component: WorkflowEditorComponent}
];

@NgModule({
  declarations: [WorkflowListComponent, WorkflowEditorComponent, DrawFlowDirective],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    RouterModule.forChild(routes)
  ]
})
export class WorkflowsModule { }
