import { HttpHeaders, HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";
import { PhoneModel } from "../_models/phone";

@Injectable({
    providedIn: 'root'
  })

export class PhoneService {
    apiUrl = environment.phoneValidatorApi;

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private http: HttpClient) { }

    getCustomers(country?: string, state?: boolean):Observable<PhoneModel[]>{
        let url = "";
        if(country){
            url += '?country=' + country;
            if(state != null) 
                url += '&state=' + state;

        }
        else if(state != null) url += '?state=' + state;

        return this.http.get<PhoneModel[]>(this.apiUrl + url, this.httpOptions);
    }

}