import {Filter} from '../model/filter';
import {PageResult} from '../model/page-result';
import {Component, OnInit} from '@angular/core';
import {EvolvingObjectService} from '../service/evolving-object.service';

@Component({
  templateUrl: './evolving-object-list.component.html'
})
export class EvolvingObjectListComponent implements OnInit {
  public evoSequence: string;

  public page = 1;
  public pageSize = 25;
  public pageFilters: Filter[] = [];
  public count: number;

  public evolvingObjects: any[];

  public loadingImageVisible: boolean;

  constructor(
    private evolvingObjectService: EvolvingObjectService) {}

  ngOnInit() {
    this.onEvolvingObjectsLoading(this.page);
  }

  public onFiltersChanged(pageFilters: Filter[]): void {
    this.pageFilters = pageFilters;
    this.onEvolvingObjectsLoading(1);
  }

  public onEvolvingObjectsLoading(page: number): void {
    this.loadingImageVisible = true;
    this.evolvingObjectService.getEvolvingObjects(page, this.pageSize, this.pageFilters)
      .subscribe(this.onEvolvingObjectsLoaded.bind(this));
  }

  private onEvolvingObjectsLoaded(pageResult: PageResult): void {
    this.loadingImageVisible = false;
    this.count = pageResult.count;
    this.evolvingObjects = pageResult.results;
  }
}
