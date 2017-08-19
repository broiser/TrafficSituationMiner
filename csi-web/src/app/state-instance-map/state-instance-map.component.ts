import {GeometryService} from '../service/geometry.service';
import {Component, OnInit, Input} from '@angular/core';
import {isNullOrUndefined} from 'util';

declare var L: any;
declare var $: any;

@Component({
  selector: 'app-state-instance-map',
  template: '<div id="map" style="height: 400px; text-align: center; font-weight: bold;"></div>'
})
export class StateInstanceMapComponent implements OnInit {

  @Input()
  public situationEvolutionId: number;

  constructor(
    private geometryService: GeometryService) {}

  ngOnInit(): void {
    this.geometryService.getSituationEvolutionGeometry(this.situationEvolutionId)
      .subscribe(this.onSituationEvolutionGeometryLoaded.bind(this));
  }

  private onSituationEvolutionGeometryLoaded(geojsonFeature: any) {
    console.dir(geojsonFeature);
    const viewPoint = geojsonFeature.geometry.coordinates[0][0];
    if (isNullOrUndefined(viewPoint)) {
      $('#map').html('Error, no Route available.');
      $('#map').height('10px');
    } else {
      const map = L.map('map');
      L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      }).addTo(map);
      const layer = L.geoJSON().addTo(map).addData(geojsonFeature);
      map.fitBounds(layer.getBounds());
    }
  }
}
