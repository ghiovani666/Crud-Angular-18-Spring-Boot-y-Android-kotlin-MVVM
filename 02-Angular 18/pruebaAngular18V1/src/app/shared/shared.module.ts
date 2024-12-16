import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotFoundComponent } from './components/errors/not-found/not-found.component';
import { ValidationMessagesComponent } from './components/errors/validation-messages/validation-messages.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { NotificationComponent } from './components/modals/notification/notification.component';
import { ModalModule } from 'ngx-bootstrap/modal';

@NgModule({ declarations: [
        NotFoundComponent,
        ValidationMessagesComponent,
        NotificationComponent,
    ],
    exports: [
        RouterModule,
        ReactiveFormsModule,
        HttpClientModule,
        ValidationMessagesComponent,
    
    ], imports: [
        CommonModule,
        RouterModule,
        ReactiveFormsModule,
        ModalModule.forRoot()], providers: [provideHttpClient(withInterceptorsFromDi())] })
export class SharedModule { }
