import { Component, OnInit } from '@angular/core';
import { CarService } from '../service/car.service';

@Component({
  selector: 'app-car-details',
  templateUrl: './car-details.component.html',
  styleUrls: ['./car-details.component.css']
})
export class CarDetailsComponent implements OnInit {
// result:any
allDetails:any
  constructor(private car:CarService) { }

  ngOnInit() :void {
    this.car.getData().subscribe((data:any)=>{
      console.log(data);
    
      
      this.allDetails=data.allCarDetails
      console.log(this.allDetails);
      

      // this.result=data
      // console.log(this.result);
      

    })
  }
  delete(id:any){
    this.car.deleteCarDetails(id).subscribe((data:any)=>{
      console.log(data);
      
    })
  }
  logout(){
    localStorage.clear();
  
  }


}
