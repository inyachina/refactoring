import {Injectable} from '@angular/core';
import {HttpService} from "./http/http.service";
import {HumanType} from "../data/types";
import {HUMAN_URL, ORDER_URL} from "../data/server_urls";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HumanService {

  constructor(private _http: HttpService) {
  }

  public createHuman(human: HumanType) {
    return this._http.postData(`${HUMAN_URL}/create`, human);
  }

  public getPeople(): Observable<HumanType[]> {
    return this._http.getData<HumanType[]>(`${HUMAN_URL}/all`)
      .pipe(map((r) => r.data));
  }

  public getHumanOrders() {
    return this._http.getData<HumanType[]>(`${ORDER_URL}/human-fate/active`)
      .pipe(map((r) => r.data));
  }
}
