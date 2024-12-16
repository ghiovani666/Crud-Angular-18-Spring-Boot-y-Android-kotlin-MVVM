import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login/login.component';
import { AccountRoutingModule } from './account-routing.module';
// import { SharedModule } from '../shared/shared.module';

@NgModule({
  declarations: [
    LoginComponent,
   ],
  imports: [
    // SharedModule,
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    
    AccountRoutingModule,
    // ...
    
  ]
})
export class AccountModule { }
