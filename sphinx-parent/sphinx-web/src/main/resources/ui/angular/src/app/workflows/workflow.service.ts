import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Page } from '../shared/page.model';
import { Workflow } from './workflow.model';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {

  private url = "http://localhost:8080/workflows";
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }), withCredentials: true };

  constructor(private http: HttpClient) { }

  exists(id: number, name: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.url}/${id}?name=${name}`);
  }

  findSuggestions(query: string): Observable<Workflow[]> {
    return this.http.get<Workflow[]>(`${this.url}?q=${query}`);
  }

  findAll(params: any): Observable<Page<Workflow>> {
    return this.http.post<Page<Workflow>>(`${this.url}/pages`, params, this.options);
  }

  validate(workflow: Workflow): Observable<boolean> {
    return this.http.post<boolean>(`${this.url}?op=validate`, workflow);
  }

  findById(id: number): Observable<Workflow> {
    return this.http.get<Workflow>(`${this.url}/${id}`);
  }
}
