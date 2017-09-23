import {GeometryService} from '../service/geometry.service';
import {Component, OnInit, Input} from '@angular/core';
import {isNullOrUndefined} from 'util';
import * as $ from 'jquery/dist/jquery.min.js';
import * as L from 'leaflet/dist/leaflet.js';

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
    if (isNullOrUndefined(geojsonFeature.geometry.coordinates[0][0])) {
      $('#map').height('10px');
      $('#map').html('Error, no routes are available.');
    } else {
      const map = L.map('map');
      const titleLayer = L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
      }).addTo(map);
      const geoJsonlayer = L.geoJSON().addTo(map).addData(geojsonFeature);
      map.fitBounds(geoJsonlayer.getBounds());
    }
  }
}
