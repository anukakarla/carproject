import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CarService } from '../service/car.service';

@Component({
  selector: 'app-update-details',
  templateUrl: './update-details.component.html',
  styleUrls: ['./update-details.component.css']
})
export class UpdateDetailsComponent implements OnInit {

  constructor(private car: CarService, private route: ActivatedRoute,private router:Router) { }

  data: any
  result: any
  selectedCarDetails: any
  ngOnInit(): void {
    let id = this.route.snapshot.params['id']
    console.log(id);
    this.car.getData().subscribe((data: any) => {
      this.result = data.allCarDetails
      for (let r of this.result) {
        if (r.id == id) {
          console.log(r,"value");
          
          this.selectedCarDetails = r
          // console.log(this.selectedCarDetails,"----****---");
        }

      }

    })

  }
  updatecarDetails(form:NgForm){
    this.car.updateDetails(this.selectedCarDetails.id,form.value).subscribe((res:any)=>{
      console.log(res);
      window.alert("Updated successfully")
    })
    this.router.navigate(["/getallcars"])


}
logout(){
  localStorage.clear();
  this.router.navigate(["/home"])
  


}
}

