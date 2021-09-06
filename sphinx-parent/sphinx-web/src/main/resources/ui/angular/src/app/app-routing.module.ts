import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {path: "forms", loadChildren: () => import('./forms/forms.module').then(m => m.FormModule)},
  {path: "workflows", loadChildren: () => import('./workflows/workflows.module').then(m => m.WorkflowsModule)},
  {path: "requests", loadChildren: () => import('./access-request/request.module').then(m => m.RequestModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
