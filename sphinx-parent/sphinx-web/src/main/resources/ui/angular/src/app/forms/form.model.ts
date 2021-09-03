import { Workflow } from "../workflows/workflow.model";

export interface Form {

    id?: number;
    name?: string;
    workflow?: Workflow;
    components?: any[] | string;
}
