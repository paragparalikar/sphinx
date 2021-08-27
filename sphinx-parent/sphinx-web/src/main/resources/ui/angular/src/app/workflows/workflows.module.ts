import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkflowListComponent } from './workflow-list/workflow-list.component';
import { WorkflowEditorComponent } from './workflow-editor/workflow-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ComponentProviderOptions, DefaultLabelComponent, DefaultLinkComponent, DefaultNodeComponent, DefaultPortComponent, RxZuDefaultsModule, RxZuModule } from '@rxzu/angular';

const routes: Routes = [
  {path: '', component: WorkflowListComponent},
  {path: 'editor', component: WorkflowEditorComponent}
];
const DEFAULTS: ComponentProviderOptions[] = [
  {
    type: 'node',
    component: DefaultNodeComponent,
  },
  {
    type: 'port',
    component: DefaultPortComponent,
  },
  {
    type: 'link',
    component: DefaultLinkComponent,
  },
  {
    type: 'label',
    component: DefaultLabelComponent,
  },
];

@NgModule({
  declarations: [WorkflowListComponent, WorkflowEditorComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    NgbModule,
    RxZuDefaultsModule,
    RxZuModule.withComponents(DEFAULTS),
    RouterModule.forChild(routes)
  ]
})
export class WorkflowsModule { }
