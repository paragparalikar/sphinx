import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Page } from '../shared/page.model';
import { AccessRequest } from './access-request.model';

@Injectable({
  providedIn: 'root'
})
export class AccessRequestService {

  private url = 'http://localhost:8080/requests';
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  findAll(params: any): Observable<Page<AccessRequest>> {
    const pageRequest = JSON.stringify(params);
    return this.httpClient.post<Page<AccessRequest>>(`${this.url}/pages`, pageRequest, this.options);
  }

  save(request: AccessRequest): Observable<AccessRequest> {
    if(request.id){
      return this.httpClient.put<AccessRequest>(`${this.url}`, request);
    } else {
      return this.httpClient.post<AccessRequest>(`${this.url}`, request);
    }
  }

  findById(id: number): Observable<AccessRequest> {
    return this.httpClient.get<AccessRequest>(`${this.url}/${id}`);
  }

  deleteById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/${id}`);
  }
}
