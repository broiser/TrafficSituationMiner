import {Filter} from '../model/filter';
import {PageResult} from '../model/page-result';
import {SituationEvolutionService} from '../service/situation-evolution.service';
import {Component, OnInit} from '@angular/core';

@Component({
  templateUrl: './situation-evolution-list.component.html'
})
export class SituationEvolutionListComponent implements OnInit {
  public page = 1;
  public pageSize = 25;
  public pageFilters: Filter[] = [];
  public count: number;

  public situationEvolutions: any[];

  public loadingImageVisible: boolean;

  constructor(
    private situationEvolutionService: SituationEvolutionService) {}

  ngOnInit() {
    this.onSituationEvolutionsLoading(this.page);
  }

  public onFiltersChanged(pageFilters: Filter[]): void {
    this.pageFilters = pageFilters;
    this.onSituationEvolutionsLoading(1);
  }

  public onSituationEvolutionsLoading(page: number): void {
    this.loadingImageVisible = true;
    this.situationEvolutionService.getSituationEvolutions(page, this.pageSize, this.pageFilters)
      .subscribe(this.onSituationEvolutionsLoaded.bind(this));
  }

  private onSituationEvolutionsLoaded(pageResult: PageResult): void {
    this.loadingImageVisible = false;
    this.count = pageResult.count;
    this.situationEvolutions = pageResult.results;
  }

}
