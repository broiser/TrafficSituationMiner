import {Component, OnInit} from '@angular/core';
import {TrafficTypeService} from '../service/traffic-type.service';

@Component({
  selector: 'app-traffic-type-list',
  templateUrl: './traffic-type-list.component.html'
})
export class TrafficTypeListComponent implements OnInit {
  public type: string;
  public trafficTypes: any[];

  public loadingImageVisible: boolean;

  constructor(
    private trafficTypeService: TrafficTypeService) {}

  ngOnInit() {
    this.loadingImageVisible = true;
    this.trafficTypeService.getTrafficTypes().subscribe(this.onTrafficTypesLoaded.bind(this));
  }

  private onTrafficTypesLoaded(trafficTypes: any[]): void {
    this.loadingImageVisible = false;
    this.trafficTypes = trafficTypes;
  }

}
