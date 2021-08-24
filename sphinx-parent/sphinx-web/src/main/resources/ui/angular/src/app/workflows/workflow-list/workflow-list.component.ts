import { Component, OnInit } from '@angular/core';
import { Workflow } from '../workflow.model';
import { WorkflowService } from '../workflow.service';

@Component({
  selector: 'app-workflow-list',
  templateUrl: './workflow-list.component.html',
  styleUrls: ['./workflow-list.component.css']
})
export class WorkflowListComponent implements OnInit {

  collectionSize: number = 0;
  page: number = 1;
  items: Workflow[] = [];

  constructor(private workflowService: WorkflowService) { }

  ngOnInit(): void {
    this.workflowService.findAll().subscribe(
      page => {
        this.items = page.content;
        this.collectionSize = page.totalElements;
        this.page = 1;
      }
    );
  }

}
