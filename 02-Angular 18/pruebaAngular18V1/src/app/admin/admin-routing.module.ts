import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './employee/list/list-employee.component';
import { AddEditMemberComponent } from './employee/edit/add-edit-employee.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  {
    path: '',
    children: [
      { path: '', component: AdminComponent },
      // path for creating a new member
      { path: 'create-employee', component: AddEditMemberComponent },
      // path for editing an existing member
      { path: 'edit-employee/:id', component: AddEditMemberComponent },
    ]
  },
]
@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AdminRoutingModule { }
