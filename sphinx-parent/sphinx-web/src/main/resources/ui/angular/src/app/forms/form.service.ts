import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Page } from '../shared/page.model';
import { Form } from './form.model';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  private url = 'http://localhost:8080/forms';
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) {}

  findSuggestions(query: string): Observable<Form[]> {
    return this.httpClient.get<Form[]>(`${this.url}?q=${query}`);
  }

  findAll(params: any): Observable<Page<Form>> {
    return this.httpClient.post<Page<Form>>(`${this.url}/pages`, params, this.options);
  }

  save(form: Form): Observable<Form> {
    form.components = JSON.stringify(form.components);
    if(form.id){
      return this.httpClient.put(`${this.url}`, form);
    } else {
      return this.httpClient.post(`${this.url}`, form);
    }
  }

  findById(id: number): Observable<Form> {
    return this.httpClient.get<Form>(`${this.url}/${id}`).pipe(
      map(form => {
        form.components = JSON.parse(form.components as string);
        return form;
      })
    );
  }

  deleteById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/${id}`);
  }

}
