import { ApplicationRef, ComponentFactory, ComponentFactoryResolver, ComponentRef, ElementRef, Injector } from "@angular/core";
import Drawflow from "drawflow";
import nodes from "src/assets/workflow-nodes.json";
import { ApprovalComponent } from "../../../workflows/workflow-editor/nodes/approval/approval.component";
import { DynamicNodeComponent } from "../../../workflows/workflow-editor/nodes/dynamic-node-component";
import { EmailComponent } from "../../../workflows/workflow-editor/nodes/email/email.component";
import { LdapComponent } from "../../../workflows/workflow-editor/nodes/ldap/ldap.component";
import { RequestComponent } from "../../../workflows/workflow-editor/nodes/request/request.component";
import { TransformerComponent } from "../../../workflows/workflow-editor/nodes/transformer/transformer.component";
import { Workflow } from "../../../workflows/workflow.model";

export class DrawflowManager {

    counter = 0;
    drawFlow?: Drawflow;
    domParser = new DOMParser();

    constructor(
        private injector: Injector,
        private applicationRef: ApplicationRef,
        private componentFactoryResolver: ComponentFactoryResolver) {
    }

    public init(hostElement: any) {
        this.drawFlow = new Drawflow(hostElement);
        this.drawFlow!.reroute = true;
        this.drawFlow!.reroute_fix_curvature = true;
        this.drawFlow!.force_first_input = false;
        this.drawFlow!.start();
    }

    public export(): any { return this.drawFlow!.export(); }

    public zoomIn() { this.drawFlow?.zoom_in(); }

    public zoomOut() { this.drawFlow?.zoom_out(); }

    public setWorkflow(workflow: Workflow, disabled: boolean) {
        this.counter = 0;
        this.drawFlow?.import({
            drawflow: {
                Home: {
                    data: workflow.data
                }
            }
        });
        this.attachComponents(disabled);
    }

    private attachComponents(disabled: boolean) {
        nodes.forEach(item => {
            this.drawFlow?.getNodesFromName(item.type)
                .map(id => this.drawFlow?.getNodeFromId(id))
                .forEach(node => {
                    const div = this.getNativeElement(node?.html);
                    this.attachComponent(node!.name, node?.data, div!, disabled);
                    this.drawFlow?.updateConnectionNodes(node!.id);
                });
        });
    }

    private attachComponent(type: string, data: any, nativeElement: HTMLElement | null, disabled: boolean) {
        const componentFactory: ComponentFactory<DynamicNodeComponent> | undefined = this.resolveComponentFactory(type);
        if (componentFactory) {
            const componentRef: ComponentRef<DynamicNodeComponent> = componentFactory.create(this.injector, [], nativeElement);
            componentRef.instance.setData(data);
            componentRef.instance.setDisabled(disabled);
            this.applicationRef.attachView(componentRef.hostView);
        }
    }

    private resolveComponentFactory(type: string): ComponentFactory<DynamicNodeComponent> | undefined {
        switch (type) {
            case 'request':
                return this.componentFactoryResolver.resolveComponentFactory(RequestComponent);
            case 'email':
                return this.componentFactoryResolver.resolveComponentFactory(EmailComponent);
            case 'approval':
                return this.componentFactoryResolver.resolveComponentFactory(ApprovalComponent);
            case 'ldap':
                return this.componentFactoryResolver.resolveComponentFactory(LdapComponent);
            case 'transformer':
                return this.componentFactoryResolver.resolveComponentFactory(TransformerComponent);
            default:
                return undefined;
        }
    }

    private getNativeElement(html?: string) {
        if (html) {
            const htmlElement = this.domParser.parseFromString(html, 'text/html');
            const div: HTMLDivElement = htmlElement.getElementsByTagName('div')[0];
            const id: string | null = div.getAttribute('id');
            return document.getElementById(id!);
        }
        return undefined;
    }

    public drop(event) {
        event.preventDefault();
        const nodetype = event.dataTransfer.getData('node');
        this.addNode(nodetype, event.clientX, event.clientY);
    }

    public addNode(type: string, pos_x: number, pos_y: number) {
        const editor: any = this.drawFlow;
        const data = { type: type };
        pos_x = pos_x * (editor.precanvas.clientWidth / (editor.precanvas.clientWidth * editor.zoom)) - (editor.precanvas.getBoundingClientRect().x * (editor.precanvas.clientWidth / (editor.precanvas.clientWidth * editor.zoom)));
        pos_y = pos_y * (editor.precanvas.clientHeight / (editor.precanvas.clientHeight * editor.zoom)) - (editor.precanvas.getBoundingClientRect().y * (editor.precanvas.clientHeight / (editor.precanvas.clientHeight * editor.zoom)));
        const nodeItem = nodes.find(item => item.type == type);
        const id: string = 'dynamic-node-' + ++this.counter;
        editor.addNode(type, nodeItem?.inputs, nodeItem?.outputs, pos_x, pos_y, type, data, `<div id="${id}"></div>`, false);
        const nativeElement = document.getElementById(id);
        this.attachComponent(type, data, nativeElement, false);
    }

}
