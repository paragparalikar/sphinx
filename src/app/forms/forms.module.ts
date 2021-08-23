import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { FormListComponent } from './form-list/form-list.component';
import { FormEditorComponent } from './form-editor/form-editor.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormioModule } from '@formio/angular';
import { FormsModule } from '@angular/forms';
import { ConfirmationModule } from '../shared/confirmation/confirmation.module';

const routes: Routes = [
  {path: '', component: FormListComponent},
  {path: 'editor', component: FormEditorComponent}
];

@NgModule({
  declarations: [FormListComponent, FormEditorComponent],
  imports: [
    CommonModule,
    NgbModule,
    FormioModule,
    FormsModule,
    ConfirmationModule,
    RouterModule.forChild(routes)
  ]
})
export class FormModule { }
