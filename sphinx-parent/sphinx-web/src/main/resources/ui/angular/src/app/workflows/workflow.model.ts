export class Workflow {
    constructor(
        public id?: number, 
        public name?: string,
        public nameMutable: boolean = true,
        public data?: any){}
}
