import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccessRequestListComponent } from './access-request-list/access-request-list.component';
import { AccessRequestEditorComponent } from './access-request-editor/access-request-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';

const routes: Routes = [
  {path: '', component: AccessRequestListComponent},
  {path: '/editor', component: AccessRequestEditorComponent}
];

@NgModule({
  declarations: [
    AccessRequestListComponent,
    AccessRequestEditorComponent
  ],
  imports: [
    CommonModule,
    TableModule,
    ButtonModule,
    ToolbarModule,
    ToastModule,
    InputTextModule,
    ConfirmPopupModule,
    RouterModule.forChild(routes)
  ]
})
export class AccessRequestModule { }
