export interface Request {

    id?: number;
    targetId?: number;
    targetName?: string;
    status?: string;
    type?: string;
    createTimestamp?: Date;
    updateTimestamp?: Date;

    payload?: string;

}
