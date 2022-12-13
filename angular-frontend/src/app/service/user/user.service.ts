import { Injectable } from '@angular/core';
import {HttpService} from '../http/http.service';
import {Observable} from "rxjs";
import { AuthorizationRequest } from "./userType";
import {LOGIN_URL, REGISTRY_URL} from "../../data/server_urls";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private _http: HttpService,
  ) {}

  public authenticate(): Observable<any> {
    return this._http
      .getData(`${LOGIN_URL}/`);
  }

  public authorizate(authorizationRequest: AuthorizationRequest): Observable<any> {
    return this._http
      .postData(`${REGISTRY_URL}/`, authorizationRequest);
  }
}
