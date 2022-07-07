import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-third',
  templateUrl: './third.component.html',
  styleUrls: ['./third.component.scss']
})
export class ThirdComponent implements OnInit {

  constructor(private router :Router,) { }

  ngOnInit(): void {
  }
  submit(){
   
    this.router.navigate(['four']);

  }

}
