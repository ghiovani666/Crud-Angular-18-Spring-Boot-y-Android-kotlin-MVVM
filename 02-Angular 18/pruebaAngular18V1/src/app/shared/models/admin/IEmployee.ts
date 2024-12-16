import { IArea } from "./IArea";

export interface IEmployee {
    id?: string;
    names: string;
    email: string;
    phone: string;
    area:  any;
    // area:  IArea;
}

 


// export interface MemberAddEdit {
//     id?: string;
//     names: string;
//     email: string;
//     phone: string;
//     area:  Area;
// }

 
// export interface Area {
//     id: number;
//     names?: string;
//  }

// export interface MemberView {
//     id?: string;
//     names: string;
//     email: string;
//     phone: string;
//     area:  [];
// }
