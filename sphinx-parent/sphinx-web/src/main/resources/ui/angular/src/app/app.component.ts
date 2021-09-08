import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { AuthenticationService } from './users/authentication.service';
import { User } from './users/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  
  title = 'sphinx';
  user: User | null = null;

  constructor(
    private primengConfig: PrimeNGConfig,
    private authenticationService: AuthenticationService){}

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.authenticationService.user.subscribe(
      user => this.user = user
    );
  }

  logout(){
    this.authenticationService.logout();
  }
}
