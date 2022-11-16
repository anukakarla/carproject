import { HttpClient, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  

  getData(){
    return this.http.get("http://localhost:8082/getallcars");
  }
  deleteCarDetails(id:any){
    return this.http.delete(`http://localhost:8082/deletecar/${id}`)
  }
  addDetails(data:any){

    return  this.http.post("http://localhost:8082/addcar",data)

  }
  updateDetails(id:any,car:any){
    return this.http.put(`http://localhost:8082/updatecar/${id}`,car)
  }

  authenticate(data:any){
    return this.http.post("http://localhost:8082/login",data);
  }

  superAdminGetData(){
    return this.http.get("http://localhost:8082/super/getallDetails")
  }
  


  constructor(private http:HttpClient) { }
}
