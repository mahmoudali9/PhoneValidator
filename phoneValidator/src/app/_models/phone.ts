export class PhoneModel{
    id: number;
    name: string;
    country: string;
    state: boolean;
    phone: string;
    countryCode: string;

    constructor(id: number, name: string, country: string, state: boolean, phone: string, countryCode: string){
        this.id = id;
        this.name = name;
        this.country = country;
        this.state = state;
        this.phone = phone;
        this.countryCode = countryCode;
    }
}