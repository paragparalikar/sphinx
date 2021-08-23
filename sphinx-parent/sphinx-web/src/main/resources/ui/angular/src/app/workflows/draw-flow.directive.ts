import { Directive, ElementRef, OnInit } from '@angular/core';
import DrawFlow from 'drawflow';

@Directive({
  selector: '[appDrawFlow]'
})
export class DrawFlowDirective implements OnInit {

  editor?: DrawFlow;

  constructor(private hostElRef: ElementRef) {}

  ngOnInit() {
    if (!!this.hostElRef?.nativeElement) {
      this.initDrawFlow(this.hostElRef["nativeElement"]);
    }
  }

  private initDrawFlow(el: HTMLElement): void {
    try {
      if (!!el) {
        this.editor = new DrawFlow(el);
        this.editor.reroute = true;
        this.editor.editor_mode = 'edit';
        this.editor.start();
      } else {
        console.error('Drawflow host element does not exist');
      }
    } catch (exception) {
      console.error('Unable to start Drawflow', exception);
    }
  }

}
