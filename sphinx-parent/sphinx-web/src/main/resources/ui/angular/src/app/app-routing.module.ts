import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './users/auth.guard';
import { LoginComponent } from './users/login/login.component';

const routes: Routes = [
  {path: "login", component: LoginComponent},
  {path: "forms", canActivate: [AuthGuard], loadChildren: () => import('./forms/forms.module').then(m => m.FormModule)},
  {path: "workflows", canActivate: [AuthGuard], loadChildren: () => import('./workflows/workflows.module').then(m => m.WorkflowsModule)},
  {path: "requests", canActivate: [AuthGuard], loadChildren: () => import('./requests/request.module').then(m => m.RequestModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
