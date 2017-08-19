import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class GeometryService {

  private BASE_URL = '/rest/geometry';

  constructor(private http: HttpClient) {}

  public getSituationEvolutionGeometry(id: number): Observable<any> {
    return this.http.get(this.BASE_URL + '/situationEvolution/' + id);
  }

}
