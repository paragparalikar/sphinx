import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Confirmation } from './confirmation.model';

@Injectable({
  providedIn: 'root'
})
export class ConfirmationService {
  private subject = new Subject<Confirmation>();

  confirm(confirmation: Confirmation): any{
    const that = this;
    const yfn = confirmation.yesFn;
    const nfn = confirmation.noFn;
    confirmation.yesFn = () => {
      yfn();
      that.subject.next();
    };
    confirmation.noFn = () => {
      nfn();
      that.subject.next();
    };
    this.subject.next(confirmation);
  }

  getMessage(): Observable<any> {
    return this.subject.asObservable();
  }
  
}
 