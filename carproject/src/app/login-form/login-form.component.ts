import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CarService } from '../service/car.service';



@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  result:any
  data:any

loginForm=new FormGroup(
  {username:new FormControl("",[Validators.required,Validators.maxLength(20)]),
  password:new FormControl("",[Validators.required])}
)
  token: any;
  role: any;

 get username(){
   return this.loginForm.get("username")

}
get password(){
  return this.loginForm.get("password")
}
// role:any
login(){
  console.log(this.loginForm.value);
  this.car.authenticate(this.loginForm.value).subscribe((val:any)=>{
    this.token=val;
      this.role=this.token.role
    // this.token=this.token.token
    console.log(val);
    console.log(this.token.token);
    
    
    // console.log(val);
    localStorage.setItem('jwttoken',this.token.token)
    if(this.token!=null && this.role==="ROLE_ADMIN"){
    this.route.navigate(['/navbar'])}
    else{
      this.route.navigate(["/superadmin"])
    }

    // this.jwttoken=val
    // this.jwttoken=this.jwttoken.token
    // console.log(this.jwttoken);
    // localStorage.setItem("jwttoken",this.jwttoken)
    

    
  })
  
}





  constructor(private route:Router,private car:CarService ) {
    
  }
  
  ngOnInit(): void {
    


    

  }

}
