import { Directive, forwardRef, Input } from '@angular/core';
import { AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors } from '@angular/forms';
import { Observable } from 'rxjs';
import { WorkflowService } from './workflow.service';

@Directive({
  selector: '[appWorkflowNameValidator]',
  providers: [
    {
        provide: NG_ASYNC_VALIDATORS,
        useExisting: forwardRef(() => WorkflowNameValidatorDirective), multi: true
    }
]
})
export class WorkflowNameValidatorDirective implements AsyncValidator {

  @Input() workflowId?: number;

  constructor(private workflowService: WorkflowService) { }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return new Promise(resolve => {
      console.log(control.value);
      this.workflowService.exists(this.workflowId ? this.workflowId : 0, control.value).subscribe(
        response => {
          if(response){
            resolve({exists: {value: true}});
          } else {
            resolve(null);
          }
        }
      );
    });
  }

}
