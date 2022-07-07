import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatBottomSheet } from '@angular/material/bottom-sheet';
import {MatButtonModule} from '@angular/material/button';
import { Router } from '@angular/router';
import { BottomComponent } from '../bottom/bottom.component';
import { Bottom1Component } from '../bottom1/bottom1.component';
import { BottomsalComponent } from '../bottomsal/bottomsal.component';

@Component({
  selector: 'app-second',
  templateUrl: './second.component.html',
  styleUrls: ['./second.component.scss']
})

export class SecondComponent implements OnInit {
  registerForm = new FormGroup({
    name: new FormControl('', [Validators.pattern(/\s/), Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$')]),
    confirmPassword: new FormControl('', Validators.required)
  });
  constructor(private _bottomSheet: MatBottomSheet,
    private router :Router,) { }

  onSubmit(): void {
    // display some fireworks
  }
  prompt(){
    alert('clicked')
  }
  ngOnInit(): void {
  }
  openBottomSheet1(): void {
    this._bottomSheet.open(BottomComponent,    {
      panelClass: 'full-width'
    });
  }
  openBottomSheet2(): void {
    this._bottomSheet.open(Bottom1Component);
  }
  openBottomSheet3(): void {
    this._bottomSheet.open(BottomsalComponent);
  }
  submit(){
   
    this.router.navigate(['third']);

  }

}

