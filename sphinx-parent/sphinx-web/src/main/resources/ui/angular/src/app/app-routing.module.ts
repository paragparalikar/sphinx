import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './users/auth.guard';
import { LoginComponent } from './users/login/login.component';

const routes: Routes = [
  {path: "", redirectTo: "home", pathMatch: "full"},
  {path: "login", component: LoginComponent},
  {path: "home", canActivate: [AuthGuard], component: HomeComponent},
  {path: "forms", canActivate: [AuthGuard], loadChildren: () => import('./forms/forms.module').then(m => m.FormModule)},
  {path: "workflows", canActivate: [AuthGuard], loadChildren: () => import('./workflows/workflows.module').then(m => m.WorkflowsModule)},
  {path: "requests", canActivate: [AuthGuard], loadChildren: () => import('./requests/request.module').then(m => m.RequestModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
