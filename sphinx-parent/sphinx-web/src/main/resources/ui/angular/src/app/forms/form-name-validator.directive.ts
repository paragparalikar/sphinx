import { Directive, forwardRef, Input } from '@angular/core';
import { AbstractControl, AsyncValidator, NG_ASYNC_VALIDATORS, ValidationErrors, Validator } from '@angular/forms';
import { Observable } from 'rxjs';
import { FormService } from './form.service';

@Directive({
  selector: '[appFormNameValidator]',
  providers: [
    {
        provide: NG_ASYNC_VALIDATORS,
        useExisting: forwardRef(() => FormNameValidatorDirective), multi: true
    }
]
})
export class FormNameValidatorDirective implements AsyncValidator{

  @Input() formId?: number; 

  constructor(private formService: FormService) { }

  validate(control: AbstractControl): Promise<ValidationErrors | null> | Observable<ValidationErrors | null> {
    return new Promise(resolve => {
      this.formService.exists(this.formId ? this.formId : 0, control.value).subscribe(
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
