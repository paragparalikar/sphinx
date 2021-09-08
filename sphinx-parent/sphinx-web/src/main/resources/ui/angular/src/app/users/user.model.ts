import { GrantedAuthority } from "./granted-authority.model";

export interface User {

    email: string;
    username: string;
    firstName: string;
    middleName: string;
    lastName: string;
    enabled: boolean;
    authorities: GrantedAuthority[];
    accountNonExpired: boolean;
    accountNonLocked: boolean;
    credentialsNonExpired: boolean;

}
