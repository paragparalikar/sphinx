import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import DrawFlow from 'drawflow';

@Component({
  selector: 'app-workflow-editor',
  templateUrl: './workflow-editor.component.html',
  styleUrls: ['./workflow-editor.component.css']
})
export class WorkflowEditorComponent implements OnInit {

  @ViewChild("drawFlowDiv", {read: ElementRef, static: true})
  private drawFlowDiv?: ElementRef;
  private editor?: DrawFlow;

  constructor() { }

  ngOnInit(): void {
    if(this.drawFlowDiv){
      this.editor = new DrawFlow(this.drawFlowDiv.nativeElement);
      this.editor.reroute = true;
      this.editor.editor_mode = 'edit';
      this.editor.start();
    }
  }

  drag(event: DragEvent){
    const element = event.target as HTMLElement;
    event!.dataTransfer!.setData("node", element!.getAttribute('data-node')!);
  }

  drop(event: DragEvent){
    event.preventDefault();
    var data = event.dataTransfer!.getData("node");
    this.addNodeToDrawFlow(data, event.clientX, event.clientY);
  }

  addNodeToDrawFlow(name: string, x: number, y: number){
    switch(name){
      case 'start': 
        const startHtml = `
        <div>
          <div class="title-box"><i class="fa fa-arrow-right"></i> Start</div>
        </div>
        `;
        this.editor!.addNode('Start', 0, 1, x, y, 'start', {}, startHtml, false);
        break;
      case 'stop':
        const endHtml = `
        <div>
          <div class="title-box"><i class="fa fa-circle"></i> Stop</div>
        </div>
        `;
        this.editor!.addNode('End', 0, 1, x, y, 'end', {}, endHtml, false);
        break;
      case 'mail':
        const mailHtml = `
        <div>
          <div class="title-box"><i class="fa fa-envelope"></i> Mail</div>
        </div>
        `;
        this.editor!.addNode('Mail', 0, 1, x, y, 'mail', {}, mailHtml, false);
        break;
    }
  }

}
