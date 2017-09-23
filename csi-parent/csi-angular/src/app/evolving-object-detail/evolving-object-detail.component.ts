import {EvolvingObjectService} from '../service/evolving-object.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/switchMap';

@Component({
  templateUrl: './evolving-object-detail.component.html'
})
export class EvolvingObjectDetailComponent implements OnInit {
  public evolvingObject: any;

  public loadingImageVisible: boolean;

  constructor(
    private route: ActivatedRoute,
    private evolvingObjectService: EvolvingObjectService) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap(this.onEvolvingObjectLoading.bind(this))
      .subscribe(this.onEvolvingObjectLoaded.bind(this));
  }

  private onEvolvingObjectLoading(params: ParamMap): Observable<any> {
    this.loadingImageVisible = true;
    return this.evolvingObjectService.getEvolvingObject(+params.get('id'))
  }

  private onEvolvingObjectLoaded(evolvingObject: any): void {
    this.loadingImageVisible = false;
    this.evolvingObject = evolvingObject;
  }

}
