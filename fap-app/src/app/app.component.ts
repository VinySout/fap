import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './shared/auth/authentication.service';
import { UserLogin } from './shared/model/user-login';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {  
  currentUser: UserLogin;
  title = 'fap-app';

  constructor(private router: Router,
    private authenticationService: AuthenticationService){
      this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
    }
}
