import {SortByPipe} from '../pipe/sortByPipe';
import {Component, OnInit, Input} from '@angular/core';
import {isNullOrUndefined} from 'util';

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

    let previous: any;
    this.sortByPipe.transform(this.stateInstances, 'beginTime').forEach(function(stateInstance) {
      graph.addNode(stateInstance.id + '-' + stateInstance.name);
      if (!isNullOrUndefined(previous)) {
        graph.addEdge(previous.id + '-' + previous.name, stateInstance.id + '-' + stateInstance.name);
      }
      previous = stateInstance;
    });

    const layout = new Layout(graph)
    const renderer = new Renderer('#canvas', graph, 1000, 150);
    renderer.draw()
  }

}
