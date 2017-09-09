import {Filter} from '../model/filter';
import {FilterType} from '../model/filtertype';
import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-evolving-object-filter',
  templateUrl: './evolving-object-filter.component.html'
})
export class EvolvingObjectFilterComponent implements OnInit {

  public filters: Filter[] = [];

  @Output()
  public filtersChanged = new EventEmitter<Filter[]>();

  public field2FilterType: Map<string, FilterType[]> = new Map<string, FilterType[]>();

  public ngOnInit(): void {
    this.field2FilterType.set('sources', [FilterType.EQUAL, FilterType.LIKE]);
    this.field2FilterType.set('duration', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('nrUpdates', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('nrPHRchanges', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('nrSpatialEvolutions', [FilterType.GREATER_THAN, FilterType.LESS_THAN]);
    this.field2FilterType.set('evoSequence', [FilterType.LIKE]);
  }

  public addFilter(): void {
    const filter = new Filter();
    filter.field = 'sources';
    filter.type = FilterType.EQUAL;
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
