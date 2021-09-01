import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Page } from '../shared/page.model';
import { Workflow } from './workflow.model';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {

  constructor(private http: HttpClient) { }

  workflow?: Workflow;

  findAll(): Observable<Page<Workflow>> {
    return this.http.get<Page<Workflow>>('assets/workflows.json');
  }

  findById(id: number): Observable<Workflow> {
    return new Observable(subscriber => subscriber.next(this.workflow));
  }

  save(workflow: Workflow): Observable<Workflow> {
    this.workflow = workflow;
    this.workflow.id = 1;
    return new Observable(subscriber => subscriber.next(this.workflow));
  }

  deleteById(id: number): Observable<any> {
    return new Observable(subscriber => subscriber.next());
  }

}
