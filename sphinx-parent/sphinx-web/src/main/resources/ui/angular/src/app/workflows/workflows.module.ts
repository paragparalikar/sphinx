import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WorkflowListComponent } from './workflow-list/workflow-list.component';
import { WorkflowEditorComponent } from './workflow-editor/workflow-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ComponentProviderOptions, DefaultLabelComponent, DefaultLinkComponent, DefaultNodeComponent, DefaultPortComponent, RxZuDefaultsModule, RxZuModule } from '@rxzu/angular';
import { RequestReceivedNodeComponent } from './workflow-editor/rxzu/nodes/request-received-node/request-received-node.component';
import { EmailNodeComponent } from './workflow-editor/rxzu/nodes/email-node/email-node.component';
import { ApprovalNodeComponent } from './workflow-editor/rxzu/nodes/approval-node/approval-node.component';
import { LdapNodeComponent } from './workflow-editor/rxzu/nodes/ldap-node/ldap-node.component';
import { CustomNodeComponent } from './workflow-editor/rxzu/nodes/custom-node/custom-node.component';

const routes: Routes = [
  {path: '', component: WorkflowListComponent},
  {path: 'editor', component: WorkflowEditorComponent}
];
const DEFAULTS: ComponentProviderOptions[] = [
  {
    type: 'node',
    component: CustomNodeComponent,
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
  declarations: [WorkflowListComponent, WorkflowEditorComponent, CustomNodeComponent, RequestReceivedNodeComponent, EmailNodeComponent, ApprovalNodeComponent, LdapNodeComponent],
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
