import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormListComponent } from './form-list/form-list.component';
import { FormEditorComponent } from './form-editor/form-editor.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import {ToolbarModule} from 'primeng/toolbar';
import { ToastModule } from 'primeng/toast';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { InputTextModule } from 'primeng/inputtext';

const routes: Routes = [
  {path: '', component: FormListComponent},
  {path: 'editor', component: FormEditorComponent}
];

@NgModule({
  declarations: [FormListComponent, FormEditorComponent],
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    TableModule,
    ButtonModule,
    ToolbarModule,
    ToastModule,
    InputTextModule,
    ConfirmPopupModule,
    RouterModule.forChild(routes)
  ]
})
export class FormModule { }
