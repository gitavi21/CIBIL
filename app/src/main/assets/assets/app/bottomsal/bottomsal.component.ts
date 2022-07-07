import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-bottomsal',
  templateUrl: './bottomsal.component.html',
  styleUrls: ['./bottomsal.component.scss']
})
export class BottomsalComponent implements OnInit {

  constructor(private bottomSheet: MatBottomSheet) { }

  ngOnInit(): void {
  }
  dismiss(){
    this.bottomSheet.dismiss();

  }
}
