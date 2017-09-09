import {SortByPipe} from '../pipe/sortByPipe';
import {Component, OnInit, Input} from '@angular/core';
import {isNullOrUndefined} from 'util';
import * as Dracula from 'graphdracula/dist/dracula.min.js';

declare var require: any;

@Component({
  selector: 'app-state-instance-graph',
  template: '<div id="canvas" style="margin-bottom: 10px" ></div>'
})
export class StateInstanceGraphComponent implements OnInit {

  @Input()
  public stateInstances: any[];

  constructor(
    private sortByPipe: SortByPipe) {}

  ngOnInit() {
    const Dracula = require('graphdracula');
    const Graph = Dracula.Graph;
    const Renderer = Dracula.Renderer.Raphael;
    const Layout = Dracula.Layout.Spring;

    const graph = new Graph();

    const sortedStateInstances = this.sortByPipe.transform(this.stateInstances, 'beginTime');

    let index = sortedStateInstances.length - 1;
    for (; index >= 0; index--) {
      const currentInstanceName = this.determineStateInstanceName(sortedStateInstances[index]);
      graph.addNode(currentInstanceName);
      if (index < (sortedStateInstances.length - 1)) {
        graph.addEdge(this.determineStateInstanceName(sortedStateInstances[index + 1]), currentInstanceName);
      }
    }
    const layout = new Layout(graph);
    const renderer = new Renderer('#canvas', graph, 1000, 150);
    renderer.draw()
  }

  private determineStateInstanceName(stateInstance: any): string {
    return stateInstance.id + '-' + stateInstance.name;
  }

}
