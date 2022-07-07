import { Component, OnInit } from '@angular/core';
import { MatBottomSheet } from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-bottom1',
  templateUrl: './bottom1.component.html',
  styleUrls: ['./bottom1.component.scss']
})
export class Bottom1Component implements OnInit {

  constructor(private bottomSheet1: MatBottomSheet) { }

  ngOnInit(): void {
  }
  dismiss(){
    this.bottomSheet1.dismiss();

  }


}
