export class EmailVertex {
    to?: string;
    applicationId?: string;
    subjectTemplate?: string;
    contentTemplate?: string;
    type: string = 'email';
}
