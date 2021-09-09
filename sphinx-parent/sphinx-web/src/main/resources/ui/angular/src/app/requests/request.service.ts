import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Page } from '../shared/page.model';
import { Request } from './request.model';

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private url = 'http://localhost:8080/requests';
  private options = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

  constructor(private httpClient: HttpClient) { }

  findAll(role: string, params: any): Observable<Page<Request>> {
    const pageRequest = JSON.stringify(params);
    return this.httpClient.post<Page<Request>>(`${this.url}/pages?role=${role}`, pageRequest, this.options);
  }

  save(request: Request): Observable<Request> {
    request.payload = JSON.stringify(request.payload);
    if(request.id){
      return this.httpClient.put<Request>(`${this.url}`, request);
    } else {
      return this.httpClient.post<Request>(`${this.url}`, request);
    }
  }

  findById(id: number): Observable<Request> {
    return this.httpClient.get<Request>(`${this.url}/${id}`).pipe(
      map(request => {
        request.payload = JSON.parse(request.payload!);
        return request;
      })
    );
  }

  deleteById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.url}/${id}`);
  }
}
