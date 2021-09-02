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
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private http: HttpClient) { }

  findSuggestions(query: string): Observable<Workflow[]> {
    return this.http.get<Workflow[]>(`${this.url}?q=${query}`);
  }

  findAll(params: any): Observable<Page<Workflow>> {
    return this.http.post<Page<Workflow>>(`${this.url}/pages`, params, this.options);
  }

  save(workflow: Workflow): Observable<Workflow> {
    if(workflow.id){
      return this.http.put<Workflow>(`${this.url}`, workflow);
    } else {
      return this.http.post<Workflow>(`${this.url}`, workflow);
    }
  }

  findById(id: number): Observable<Workflow> {
    return this.http.get<Workflow>(`${this.url}/${id}`);
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }
}
