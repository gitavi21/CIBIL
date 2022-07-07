import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-first',
  templateUrl: './first.component.html',
  styleUrls: ['./first.component.scss']
})
export class FirstComponent implements OnInit {

  constructor(private router :Router,public activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }
  submit(){
   
    this.router.navigate(['second']);

  }

}
