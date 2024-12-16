import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from './shared/components/errors/not-found/not-found.component';
const routes: Routes = [
  { path: '', loadChildren: () => import('./account/account.module').then(module => module.AccountModule) },
  {
    path: '',
    children: [
      { path: 'admin', loadChildren: () => import('./admin/admin.module').then(module => module.AdminModule) },
    ],
  },
  // { path: 'admin', loadChildren: () => import('./admin/admin.module').then(module => module.AdminModule)},
  { path: 'not-found', component: NotFoundComponent },
  { path: '**', component: NotFoundComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
