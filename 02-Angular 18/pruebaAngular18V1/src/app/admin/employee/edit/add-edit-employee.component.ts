import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedService } from 'src/app/shared/shared.service';
import { ActivatedRoute, Router } from '@angular/router';
import { IEmployee } from 'src/app/shared/models/admin/IEmployee';
import { IArea } from 'src/app/shared/models/admin/IArea';
import { AdminService } from '../../admin.service';

@Component({
  selector: 'app-add-edit-employee',
  templateUrl: './add-edit-employee.component.html',
  styleUrls: ['./add-edit-employee.component.css']
})
export class AddEditMemberComponent implements OnInit {
  employeeForm: FormGroup = new FormGroup({});
  formInitialized = false;
  addNew = true;
  submitted = false;
  errorMessages: string[] = [];
  applicationRoles: string[] = [];
  existingMemberRoles: string[] = [];

  varIdEmpleado: string = "";

  members: IEmployee[] = [];
  varFormCreate = {} as IEmployee;
  varListAreas: IArea[] = [];


  constructor(private adminService: AdminService,
    private formBuilder: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const idEmpleado = this.activatedRoute.snapshot.paramMap.get('id');
    if (idEmpleado) {
      this.varIdEmpleado = idEmpleado;
      this.addNew = false; // False: Oculta el boton de agregar
      this.getEditEmployee(idEmpleado);
    } else {
      this.initialEmployeeForm(undefined);//Listo para crear y limpiar los inputs
    }
    this.getListAreas();
  }

  initialEmployeeForm(member: IEmployee | undefined) {
    console.log('%c [test]-48', 'font-size:13px; background:pink; color:#bf2c9f;', member)
    if (member) {
      // form for editing an existing member
      this.employeeForm = this.formBuilder.group({
        id: [member.id],
        email: [member.email, Validators.required],
        phone: [member.phone, Validators.required],
        names: [member.names, Validators.required],
        area: [member.area?.id, Validators.required]
      });

    } else {
      // form for creating a member
      this.employeeForm = this.formBuilder.group({
        id: [''],
        email: ['', Validators.required],
        phone: ['', Validators.required],
        names: ['', Validators.required],
        area: ['', Validators.required],
      });
    }

    this.formInitialized = true;
  }

  getEditEmployee(id: string) {
    this.adminService.getEditEmployee(id).subscribe({
      next: response => {
        this.initialEmployeeForm(response);
      }
    })
  }

  getListAreas() {
    this.adminService.getArea().subscribe({
      next: response => {
        return this.varListAreas = response
      }
    });
  }

  submit() {
    this.errorMessages = [];
    if (this.employeeForm.valid) {

      let objectCreate = {
        names: this.employeeForm.get('names')?.value,
        email: this.employeeForm.get('email')?.value,
        phone: this.employeeForm.get('phone')?.value,
        area: { "id": this.employeeForm.get('area')?.value },
      };

      this.adminService.getCreateUpdateEmployee(objectCreate, this.varIdEmpleado, this.addNew).subscribe({
        next: (response: any) => {
          // this.sharedService.showNotification(true, response.value.titile, response.value.message);
          this.router.navigateByUrl('/admin');
        },
        error: error => {
          if (error.error.errors) {
            this.errorMessages = error.error.errors;
          } else {
            this.errorMessages.push(error.error);
          }
        }
      })
    } else {
      // this.submitted = true;
    }
  }

}
