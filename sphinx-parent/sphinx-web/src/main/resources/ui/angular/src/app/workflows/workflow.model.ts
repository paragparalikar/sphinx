export class Workflow {
    constructor(
        public id?: number, 
        public name?: string,
        public requestType: string = 'ACCESS',
        public data?: any){}
}
