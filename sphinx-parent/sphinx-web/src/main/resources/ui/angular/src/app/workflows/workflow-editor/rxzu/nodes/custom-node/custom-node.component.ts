import {
  ChangeDetectionStrategy,
  Component,
  Inject,
  IterableChanges,
  IterableDiffer,
  IterableDiffers,
  OnInit,
  ViewChild,
  ViewContainerRef,
} from '@angular/core';
import { NodeModel, PortModel } from '@rxzu/core';
import {
  filter,
  mapTo,
  pluck,
  switchMap,
  take,
  takeUntil,
  tap,
} from 'rxjs/operators';
import { combineLatest } from 'rxjs';
import { EngineService, MODEL, RxZuDiagramComponent } from '@rxzu/angular';

@Component({
  selector: 'app-custom-node',
  templateUrl: './custom-node.component.html',
  styleUrls: ['./custom-node.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CustomNodeComponent implements OnInit {
  private portDiffers: IterableDiffer<PortModel>;
  @ViewChild('portsLayer', { read: ViewContainerRef, static: true })
  portsLayer!: ViewContainerRef;

  constructor(
    @Inject(MODEL) public model: NodeModel,
    private engine: EngineService,
    private diagram: RxZuDiagramComponent,
    private iterableDiffers: IterableDiffers
  ) {
    this.portDiffers = this.iterableDiffers
      .find([])
      .create<PortModel>((index, item) => item.id);
  }

  ngOnInit() {
    this.updatePorts();
  }

  getPortsHost() {
    return this.portsLayer;
  }

  updatePorts(): void {
    this.model
      .selectPorts()
      .pipe(
        takeUntil(this.model.onEntityDestroy()),
        filter(
          (ports: PortModel[] | null | undefined): ports is PortModel[] =>
            ports !== null && ports !== undefined
        ),
        tap((ports) => this.applyPortChanges(this.portDiffers.diff(ports))),
        switchMap((ports) =>
          combineLatest(
            ports.map((port) =>
              port
                .paintChanges()
                .pipe(pluck('isPainted'), filter<boolean>(Boolean), take(1))
            )
          )
        ),
        filter((val) => val !== null),
        mapTo(true)
      )
      .subscribe(
        () => !this.model.getPainted().isPainted && this.model.setPainted(true)
      );
  }

  private applyPortChanges(changes: IterableChanges<PortModel> | null): void {
    if (changes) {
      const canvasManager = this.engine.getCanvasManager();
      changes.forEachAddedItem(({ item }) => {
        canvasManager.paintModel(item, this.getPortsHost());
        item.setParent(this.model);
        this.model.updatePortCoords(item, this.diagram.diagramEngine);
      });

      changes.forEachMovedItem(({ item }) => {
        this.model.updatePortCoords(item, this.diagram.diagramEngine);
      });
    }
  }
}
