import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {RouterModule, Routes} from '@angular/router';

import {AppComponent} from './app.component';
import {TrafficTypeListComponent} from './traffic-type-list/traffic-type-list.component';

const appRoutes: Routes = [
  {path: 'trafficType', component: TrafficTypeListComponent},
  {path: '**', component: TrafficTypeListComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    TrafficTypeListComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      appRoutes,
      {enableTracing: true} // <-- debugging purposes only
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
