import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import { Router } from '@angular/router';
import { BottomdownComponent } from '../bottomdown/bottomdown.component';

@Component({
  selector: 'app-five',
  templateUrl: './five.component.html',
  styleUrls: ['./five.component.scss']
})
export class FiveComponent implements OnInit {

  constructor(private router :Router,private bottomSheet: MatBottomSheet) { }

  ngOnInit(): void {
  }  
  submit(){
   
    this.router.navigate(['five']);

  }
  openBottomSheet(){
    this.bottomSheet.open(BottomdownComponent);


  }


}
