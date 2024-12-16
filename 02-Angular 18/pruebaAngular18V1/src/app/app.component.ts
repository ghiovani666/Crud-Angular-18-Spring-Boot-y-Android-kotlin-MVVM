import { Component, OnInit } from '@angular/core';
import { AccountService } from './account/account.service';
import { SharedService } from './shared/shared.service';
import { environment } from 'src/environments/environment.development'
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  constructor(private router: Router) { }
  ngOnInit(): void {
    this.isValidateToken();
  }

  private isValidateToken() {
    const jwt = localStorage.getItem(environment.userKey);
    if (jwt) {
      this.router.navigateByUrl('/admin');
    } else {
      this.router.navigateByUrl('/');

    }
  }


}
