import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../shared/page.model';
import { Workflow } from './workflow.model';

@Injectable({
  providedIn: 'root'
})
export class WorkflowService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<Page<Workflow>> {
    return this.http.get<Page<Workflow>>('assets/workflows.json');
  }

}
