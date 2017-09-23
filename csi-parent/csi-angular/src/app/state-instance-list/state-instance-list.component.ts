import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-state-instance-list',
  templateUrl: './state-instance-list.component.html'
})
export class StateInstanceListComponent {

  @Input()
  public stateInstances: any[];
}
