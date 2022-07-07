import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BottomsalComponent } from './bottomsal.component';

describe('BottomsalComponent', () => {
  let component: BottomsalComponent;
  let fixture: ComponentFixture<BottomsalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BottomsalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BottomsalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
