import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from '../service/car.service';

@Component({
  selector: 'app-superadmin',
  templateUrl: './superadmin.component.html',
  styleUrls: ['./superadmin.component.css']
})
export class SuperadminComponent implements OnInit {

  constructor(private car:CarService ,private route:Router) { }
allDetails:any
  ngOnInit(): void {
    this.car.superAdminGetData().subscribe((data:any)=>{
      console.log(data);
    
      
    this.allDetails=data.carDetails
      console.log(this.allDetails);
  })
}
logout(){
  localStorage.clear();
  this.route.navigate(["/home"])
  window.alert("logged Out Sucessfully")


}

}
