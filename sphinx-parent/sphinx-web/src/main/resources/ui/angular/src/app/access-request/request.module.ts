import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RequestListComponent } from './request-list/request-list.component';
import { RequestEditorComponent } from './request-editor/request-editor.component';
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

const routes: Routes = [
  {path: '', component: RequestListComponent},
  {path: 'editor', component: RequestEditorComponent}
];

@NgModule({
  declarations: [
    RequestListComponent,
    RequestEditorComponent
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
    RouterModule.forChild(routes)
  ]
})
export class RequestModule { }
