import {Filter} from '../model/filter';
import {FilterType} from '../model/filtertype';
import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-situation-evolution-filter',
  templateUrl: './situation-evolution-filter.component.html'
})
export class SituationEvolutionFilterComponent implements OnInit {

  public filters: Filter[] = [];

  @Output()
  public filtersChanged = new EventEmitter<Filter[]>();

  public field2FilterType: Map<string, FilterType[]> = new Map<string, FilterType[]>();

  public ngOnInit(): void {
    this.field2FilterType.set('evolutionSequence', [FilterType.LIKE]);
    this.field2FilterType.set('evoSteps', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('majorEvoSteps', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('totalDuration', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
  }

  public addFilter(): void {
    const filter = new Filter();
    filter.field = 'evolutionSequence';
    filter.type = FilterType.LIKE;
    filter.value = undefined;
    this.filters.push(filter);
  }

  public updateFilterType(index) {
    const filter = this.filters[index];
    filter.type = this.field2FilterType.get(filter.field)[0];
  }

  public determineFilterType(type: FilterType): string {
    return FilterType[type];
  }

  public removeFilter(index: number): void {
    this.filters.splice(index, 1);
  }

  public search(): void {
    this.filtersChanged.emit(this.filters);
  }
}
