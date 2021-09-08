import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TableModule } from 'primeng/table';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { LoginComponent } from './users/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpAuthInterceptor } from './users/http-auth.interceptor';
import { HomeComponent } from './home/home.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,   
    HttpClientModule,
    NgbModule,
    TableModule,
    ToastModule,
    ReactiveFormsModule,
    ConfirmPopupModule
  ],
  exports: [
    TableModule
  ],
  providers: [
    MessageService,
    ConfirmationService,
    { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
