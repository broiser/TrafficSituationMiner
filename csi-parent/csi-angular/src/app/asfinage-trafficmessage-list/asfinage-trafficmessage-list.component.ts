import {Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-asfinage-trafficmessage-list',
  templateUrl: './asfinage-trafficmessage-list.component.html'
})
export class AsfinageTrafficmessageListComponent {

  @Input()
  public asfinagTrafficmessages: any[];

}
