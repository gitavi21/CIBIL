import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatCardImage, MatCardModule} from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FirstComponent } from './first/first.component';
import {MatButtonModule} from '@angular/material/button';
import { AngularSvgIconModule } from 'angular-svg-icon';
import { SecondComponent } from './second/second.component';
import { MatInputModule } from '@angular/material/input';
import { ThirdComponent } from './third/third.component';
import { MatSelectModule } from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {  MatNativeDateModule, MatOptionModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import {MatBottomSheetModule} from '@angular/material/bottom-sheet';
import { Bottom1Component } from './bottom1/bottom1.component';
import { BottomsalComponent } from './bottomsal/bottomsal.component';
import { FourComponent } from './four/four.component';
import { FiveComponent } from './five/five.component';
import { BottomdownComponent } from './bottomdown/bottomdown.component';

@NgModule({
  declarations: [
    AppComponent,
    FirstComponent,
    SecondComponent,
    ThirdComponent,
    Bottom1Component,
    BottomsalComponent,
    FourComponent,
    FiveComponent,
    BottomdownComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatBottomSheetModule,
    MatSelectModule,
    MatOptionModule,
    MatDatepickerModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,MatNativeDateModule,
    MatRadioModule,
    AngularSvgIconModule.forRoot(),
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
