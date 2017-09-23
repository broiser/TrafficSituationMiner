import {SituationEvolutionService} from '../service/situation-evolution.service';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Observable} from 'rxjs/Observable';

@Component({
  templateUrl: './situation-evolution-detail.component.html'
})
export class SituationEvolutionDetailComponent implements OnInit {
  public situationEvolution: any;

  public loadingImageVisible: boolean;

  constructor(
    private route: ActivatedRoute,
    private situationEvolutionService: SituationEvolutionService) {}

  ngOnInit() {
    this.route.paramMap
      .switchMap(this.onSituationEvolutionLoading.bind(this))
      .subscribe(this.onSituationEvolutionLoaded.bind(this));
  }

  private onSituationEvolutionLoading(params: ParamMap): Observable<any> {
    this.loadingImageVisible = true;
    return this.situationEvolutionService.getSituationEvolution(+params.get('id'));
  }

  private onSituationEvolutionLoaded(situationEvolution: any): void {
    this.loadingImageVisible = false;
    this.situationEvolution = situationEvolution;
  }
}
