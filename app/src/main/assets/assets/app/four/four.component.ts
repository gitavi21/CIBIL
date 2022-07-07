import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-four',
  templateUrl: './four.component.html',
  styleUrls: ['./four.component.scss']
})
export class FourComponent implements OnInit {

  constructor(private router :Router,) { }

  ngOnInit(): void {
  }
  submit(){
   
    this.router.navigate(['five']);

  }

}
