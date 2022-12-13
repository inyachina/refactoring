import {Injectable} from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpService} from "./http/http.service";
import {ORDER_URL} from "../data/server_urls";
import {ApiResponse} from "./http/http.type";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private _http: HttpService,
  ) {
  }

  getAllEventOrders() {
    return of(null);
  }

  public getHumanFate(id: number): Observable<ApiResponse<any>> {
    return this._http.getData<ApiResponse<any>>(`${ORDER_URL}/human-fate/${id}`);
  }

  changeHumanFate(id: number, fate: string): Observable<ApiResponse<any>> {
    return this._http.putData<ApiResponse<any>>(`${ORDER_URL}/human-fate-change/${id}`, fate);
  }

  createHumanOrder(id: number): Observable<ApiResponse<any>> {
    return this._http.postData<ApiResponse<any>>(`${ORDER_URL}/human/create/${id}`);
  }
}
