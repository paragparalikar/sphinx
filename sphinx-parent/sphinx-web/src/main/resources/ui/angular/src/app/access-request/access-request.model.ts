import { Form } from "../forms/form.model";
import { User } from "../user/user.model";


export interface AccessRequest {

    id?: number;
    form: Form;
    status: string;
    createTimestamp?: Date;
    updateTimestamp?: Date;

    payload?: string;
    users?: User[];

}
