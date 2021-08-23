import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfirmationComponent } from './confirmation.component';
import { ConfirmationService } from './confirmation.service';

@NgModule({
  declarations: [ConfirmationComponent],
  imports: [CommonModule],
  exports: [ConfirmationComponent],
  providers: [ConfirmationService]
})
export class ConfirmationModule { }
