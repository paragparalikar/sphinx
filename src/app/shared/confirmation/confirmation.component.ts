import { Component, OnInit } from '@angular/core';
import { Confirmation } from './confirmation.model';
import { ConfirmationService } from './confirmation.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {
  
  confirmation?: Confirmation;

  constructor(private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.confirmationService.getMessage().subscribe(
      confirmation => {
        this.confirmation = confirmation;
      }
    );
  }

  close(){
    this.confirmation = undefined;
  }

}
