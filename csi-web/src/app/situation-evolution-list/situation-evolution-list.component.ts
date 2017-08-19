import {PageResult} from '../model/page-result';
import {SituationEvolutionService} from '../service/situation-evolution.service';
import {Component, OnInit} from '@angular/core';

@Component({
  templateUrl: './situation-evolution-list.component.html'
})
export class SituationEvolutionListComponent implements OnInit {
  public page = 1;
  public pageSize = 25;
  public count: number;

  public situationEvolutions: any[];

  public loadingImageVisible: boolean;

  constructor(
    private situationEvolutionService: SituationEvolutionService) {}

  ngOnInit() {
    this.onSituationEvolutionsLoading(this.page);
  }

  public onSituationEvolutionsLoading(page: number): void {
    this.loadingImageVisible = true;
    this.situationEvolutionService.getSituationEvolutions(page, this.pageSize).subscribe(this.onSituationEvolutionsLoaded.bind(this));
  }

  private onSituationEvolutionsLoaded(pageResult: PageResult): void {
    this.loadingImageVisible = false;
    this.count = pageResult.count;
    this.situationEvolutions = pageResult.results;
  }

}
