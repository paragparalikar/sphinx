export interface Request {

    id?: number;
    name?: string;
    targetId?: number;
    targetName?: string;
    status?: string;
    type?: string;
    createTimestamp?: Date;
    updateTimestamp?: Date;

    payload?: any;

}
