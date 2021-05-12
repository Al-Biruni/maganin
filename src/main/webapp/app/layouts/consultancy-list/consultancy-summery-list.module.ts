import { NgModule } from '@angular/core';
import { ConsultancySummeryComponent } from './consultancy-summery/consultancy-summery.component';
import { ConsultancySummeryListComponent } from './consultancy-summery-list.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [RouterModule, CommonModule, MatCardModule, FontAwesomeModule],
  declarations: [ConsultancySummeryComponent, ConsultancySummeryListComponent],
  exports: [ConsultancySummeryListComponent, ConsultancySummeryComponent],
})
export class ConsultancySummeryListModule {}
