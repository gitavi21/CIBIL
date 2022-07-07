import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-bottomdown',
  templateUrl: './bottomdown.component.html',
  styleUrls: ['./bottomdown.component.scss']
})
export class BottomdownComponent implements OnInit {

  constructor(private bottomSheet: MatBottomSheet) { }

  ngOnInit(): void {
  }
  dismiss(){
    this.bottomSheet.dismiss();

  }

}
