import {Filter} from '../model/filter';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';
import {PageResult} from '../model/page-result';

@Injectable()
export class EvolvingObjectService {

  private BASE_URL = '/rest/evolvingObject';

  constructor(private http: HttpClient) {}

  public getEvolvingObject(id: number): Observable<any> {
    return this.http.get(this.BASE_URL + '/' + id);
  }

  public getEvolvingObjects(page: number, pageSize: number, pageFilters: Filter[]): Observable<PageResult> {
    return this.http.post(this.BASE_URL + '/' + page + '/' + pageSize, pageFilters);
  }

}
