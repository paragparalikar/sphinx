import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DrawflowRendererComponent } from './drawflow-renderer/drawflow-renderer.component';



@NgModule({
  declarations: [DrawflowRendererComponent],
  imports: [
    CommonModule
  ],
  exports: [DrawflowRendererComponent]
})
export class DrawflowModule { }
