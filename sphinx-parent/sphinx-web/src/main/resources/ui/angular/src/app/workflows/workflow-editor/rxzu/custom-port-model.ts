import { createValueState, PortModel, PortModelOptions, ValueState } from "@rxzu/angular";
import { CustomPortModelOptions } from "./custom-port-model-options";

export class CustomPortModel<Extras = any> extends PortModel {

    extras$: ValueState<Extras>;

    constructor(options: CustomPortModelOptions = {}) {
        super(options);
        this.extras$ = createValueState(
            options.extras ?? {},
            this.entityPipe('extras')
          );
    }

}
