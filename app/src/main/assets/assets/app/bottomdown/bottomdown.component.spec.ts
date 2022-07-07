import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BottomdownComponent } from './bottomdown.component';

describe('BottomdownComponent', () => {
  let component: BottomdownComponent;
  let fixture: ComponentFixture<BottomdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BottomdownComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BottomdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
