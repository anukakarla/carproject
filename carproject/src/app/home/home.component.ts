import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CarService } from '../service/car.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  text: any
  filterDetails: any
  carapp: any = []
  cpy: any
  carData: any = []


  constructor(private car: CarService, private route: Router) { }

  ngOnInit(): void {
    console.log(this.text);
    this.car.getData().subscribe((data: any) => {
      this.carData = data.allCarDetails
      this.cpy = [...this.carData]
      console.log(this.carapp);


    })

  }

  searchCar() {
    if (this.text) {
      this.carapp = this.carData.filter((val: any) => {
        return val.name.toUpperCase().includes(this.text.toUpperCase())
      })
    }
    else {
      this.carapp = []
    }

  }
  logout() {
    localStorage.clear();

  }

}
