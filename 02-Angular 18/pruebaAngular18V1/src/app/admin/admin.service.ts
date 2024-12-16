import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IArea } from '../shared/models/admin/IArea';
import { IEmployee } from '../shared/models/admin/IEmployee';
import { environment } from 'src/environments/environment.development';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  getListEmployee() {
    return this.http.get<IEmployee[]>(`${environment.appUrl}/employees/list`);
  }

  getEditEmployee(id: string) {
    return this.http.get<IEmployee>(`${environment.appUrl}/employees/${id}`);
  }

  getCreateUpdateEmployee(model: IEmployee, varIdEmpleado: string, isValue: boolean) {
    if (isValue)//true: crear
      return this.http.post(`${environment.appUrl}/employees/create`, model);
    else//false: Actualizar
      return this.http.put(`${environment.appUrl}/employees/${varIdEmpleado}`, model);
  }

  getArea() {
    return this.http.get<IArea[]>(`${environment.appUrl}/area/list`);
  }

  deleteEmployee(id: string | undefined): Observable<object> {
    return this.http.delete<object>(`${environment.appUrl}/employees/${id}`);
  }
  
}
