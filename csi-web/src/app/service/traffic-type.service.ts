import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class TrafficTypeService {

  private BASE_URL = '/rest/trafficType';

  constructor(private http: HttpClient) {}

  public getTrafficTypes(): Observable<any[]> {
    return this.http.get(this.BASE_URL + '/all');
  }

}
