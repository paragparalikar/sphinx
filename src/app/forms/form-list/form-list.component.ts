import { Component, OnInit } from '@angular/core';
import { Alert } from 'src/app/shared/alert';
import { Confirmation } from 'src/app/shared/confirmation/confirmation.model';
import { ConfirmationService } from 'src/app/shared/confirmation/confirmation.service';
import { Form } from '../form.model';
import { FormService } from '../form.service';

@Component({
  selector: 'app-form-list',
  templateUrl: './form-list.component.html',
  styleUrls: ['./form-list.component.css']
})
export class FormListComponent implements OnInit {

  alerts: Alert[] = [];
  collectionSize: number = 0;
  page: number = 1;
  items: Form[] = [];

  constructor(private formService: FormService, private confirmationService: ConfirmationService) { }

  ngOnInit(): void {
    this.formService.findAll().subscribe(
      page => {
        this.items = page.items;
        this.collectionSize = page.collectionSize;
        this.page = 1;
      }
    );
  }

  delete(form: Form) {
    if (form && form.id) {
      const confirmation = new Confirmation();
      confirmation.title = "Please Confirm !";
      confirmation.message = "Are you sure you want to delete form " + form.name + " ?";
      confirmation.yesFn = () => {
        this.formService.deleteById(form!.id!).subscribe(
          resonse => {
            this.alerts.push({
              type: 'alert-success',
              text: "Form has been deleted successfully"
            });
          }
        );
      };
      this.confirmationService.confirm(confirmation);
    }
  }

}
