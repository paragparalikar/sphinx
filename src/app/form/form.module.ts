import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormListComponent } from './form-list/form-list.component';
import { FormEditorComponent } from './form-editor/form-editor.component';
import { RouterModule, Routes } from '@angular/router';
import { FormioModule } from '@formio/angular';

const routes : Routes = [
  {path: '', component: FormListComponent},
  {path: 'editor', component: FormEditorComponent}
];

@NgModule({
  declarations: [FormListComponent, FormEditorComponent],
  imports: [
    CommonModule,
    FormioModule,
    RouterModule.forChild(routes)
  ]
})
export class FormModule { }
