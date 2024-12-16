import { Component, OnInit, TemplateRef } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import { User } from '../../../shared/models/account/user';
import { ReplaySubject, map, of } from 'rxjs';
import { Router } from '@angular/router';

import { AdminService } from '../../admin.service';
import { SharedService } from '../../../shared/shared.service';
import { IEmployee } from '../../../shared/models/admin/IEmployee';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-list-employee',
  templateUrl: './list-employee.component.html',
  styleUrls: ['./list-employee.component.css']
})

export class AdminComponent implements OnInit {
  private userSource = new ReplaySubject<User | null>(1);
  user$ = this.userSource.asObservable();

  varListEmployee: IEmployee[] = [];

  varListEmployeeObject = {} as IEmployee;
  modalRef?: BsModalRef;

  constructor(private adminService: AdminService, private sharedService: SharedService, private modalService: BsModalService, private router: Router) { }

  ngOnInit(): void {
    this.adminService.getListEmployee().subscribe({
      next: response => {
        console.log('%c [test]-32', 'font-size:13px; background:pink; color:#bf2c9f;', response)
        return this.varListEmployee = response
      }
    });
  }

  deleteEmployee(id: string | undefined, template: TemplateRef<any>) {
    let member = this.find_IEmployee(id);
    if (member) {
      this.varListEmployeeObject = member;
      this.modalRef = this.modalService.show(template, { class: 'modal-sm' });
    }
  }

  confirm() {
    if (this.varListEmployeeObject) {
      this.adminService.deleteEmployee(this.varListEmployeeObject.id).subscribe({
        next: (response) => {
          // console.log('%c [test]-52', 'font-size:13px; background:pink; color:#bf2c9f;', response)
          this.sharedService.showNotification(true, 'Deleted', `Se elimino : ${this.varListEmployeeObject?.names} !`);
          this.varListEmployee = this.varListEmployee.filter(x => x.id !== this.varListEmployeeObject?.id);
          this.varListEmployeeObject = {} as IEmployee;
          this.modalRef?.hide();

        },
        error: (error) => {
          console.log('%c [test]-61', 'font-size:13px; background:pink; color:#bf2c9f;', error)
        }
      })
    }
  }

  decline() {
    this.varListEmployeeObject = {} as IEmployee;
    this.modalRef?.hide();
  }

  logout() {
    localStorage.removeItem(environment.userKey);
    this.userSource.next(null);
    this.router.navigateByUrl('/');
  }

  private find_IEmployee(id: string | undefined) {
    let varIEmployee = this.varListEmployee.find(x => x.id === id);
    if (varIEmployee) {
      return varIEmployee;
    }
    return undefined;
  }

}
