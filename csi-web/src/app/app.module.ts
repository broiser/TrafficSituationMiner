import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {SearchPipe} from './pipe/searchPipe';
import {SortByPipe} from './pipe/sortByPipe';
import {FormsModule} from '@angular/forms';
import {HttpUrlInterceptor} from './http/httpInterceptor';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';

import {PaginationComponent} from './pagination/pagination.component';

import {TrafficTypeService} from './service/traffic-type.service';
import {TrafficTypeListComponent} from './traffic-type-list/traffic-type-list.component';

import {EvolvingObjectService} from './service/evolving-object.service';
import {EvolvingObjectListComponent} from './evolving-object-list/evolving-object-list.component';
import {EvolvingObjectDetailComponent} from './evolving-object-detail/evolving-object-detail.component';

import {SituationEvolutionService} from './service/situation-evolution.service';
import {SituationEvolutionListComponent} from './situation-evolution-list/situation-evolution-list.component';
import {SituationEvolutionDetailComponent} from './situation-evolution-detail/situation-evolution-detail.component';

import {AsfinageTrafficmessageListComponent} from './asfinage-trafficmessage-list/asfinage-trafficmessage-list.component';

import {GeometryService} from './service/geometry.service';
import {StateInstanceMapComponent} from './state-instance-map/state-instance-map.component';
import {StateInstanceGraphComponent} from './state-instance-graph/state-instance-graph.component';
import {StateInstanceListComponent} from './state-instance-list/state-instance-list.component';
import { SituationEvolutionFilterComponent } from './situation-evolution-filter/situation-evolution-filter.component';
import { EvolvingObjectFilterComponent } from './evolving-object-filter/evolving-object-filter.component';

const appRoutes: Routes = [
  {path: 'trafficType', component: TrafficTypeListComponent},
  {path: 'evolvingObject', component: EvolvingObjectListComponent},
  {path: 'evolvingObject/:id', component: EvolvingObjectDetailComponent},
  {path: 'situationEvolution', component: SituationEvolutionListComponent},
  {path: 'situationEvolution/:id', component: SituationEvolutionDetailComponent},
  {path: '**', component: TrafficTypeListComponent}
];

@NgModule({
  declarations: [
    SearchPipe,
    SortByPipe,
    AppComponent,
    PaginationComponent,
    TrafficTypeListComponent,
    EvolvingObjectListComponent,
    EvolvingObjectDetailComponent,
    SituationEvolutionListComponent,
    SituationEvolutionDetailComponent,
    AsfinageTrafficmessageListComponent,
    StateInstanceListComponent,
    StateInstanceGraphComponent,
    StateInstanceMapComponent,
    SituationEvolutionFilterComponent,
    EvolvingObjectFilterComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: false} // <-- debugging purposes only
    )
  ],
  providers: [
    SortByPipe,
    TrafficTypeService,
    EvolvingObjectService,
    GeometryService,
    SituationEvolutionService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpUrlInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
