import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../shared/page.model';
import { Form } from './form.model';
import * as forms from 'src/assets/forms.json';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  forms: Form[] = [];

  constructor(private httpClient: HttpClient) { 
    forms.items.forEach(form => this.forms.push(form));
  }

  findAll(): Observable<Page<Form>> {
    const page = new Page(this.forms.length, this.forms);
    return new Observable(subscriber => subscriber.next(page));
  }

  save(form: Form): Observable<Form> {
    this.forms.push(form);
    return new Observable(subscriber => subscriber.next(form));
  }

  findById(id: number): Observable<Form> {
    const form = this.forms.find(form => id == form.id);
    return new Observable(subscriber => subscriber.next(form));
  }

  deleteById(id: number): Observable<any> {
    this.forms = this.forms.filter(form => id != form.id);
    return new Observable(subscriber => subscriber.next());
  }

}
