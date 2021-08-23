export class Confirmation {

    public title: string = "Are you sure ?";
    public titleClass: string = "text-light bg-danger";
    public message: string = "";
    public yesFn: () => void = () => undefined;
    public yesIconClass: string = "fa fa-trash";
    public yesButtonClass: string = "btn btn-danger";
    public noFn: () => void = () => undefined;
    public noIconClass: string = "fa fa-times";
    public noButtonClass: string = "btn btn-secondary";

}
