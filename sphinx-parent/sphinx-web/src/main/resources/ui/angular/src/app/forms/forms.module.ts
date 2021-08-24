import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormListComponent } from './form-list/form-list.component';
import { FormEditorComponent } from './form-editor/form-editor.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { ConfirmationModule } from '../shared/confirmation/confirmation.module';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import {ToolbarModule} from 'primeng/toolbar';
import { ChipsModule } from 'primeng/chips';
import { ConfirmPopupModule } from 'primeng/confirmpopup';

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
    ChipsModule,
    ConfirmPopupModule,
    RouterModule.forChild(routes)
  ]
})
export class FormModule { }
