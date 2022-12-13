import {Injectable} from '@angular/core';
import {HttpService} from "./http/http.service";
import {EventType, OrderEventType} from "../data/types";
import {StatusOrder} from "../data/enums";
import {EVENT_URL} from "../data/server_urls";
import {map} from "rxjs/operators";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EventService {


  constructor(
    private _http: HttpService,
  ) {
  }

  public _getEvents() {
    // return this._http
    //   .getData(`${EVENT_URL}/`);
    const events: Array<EventType> = [{
      name: `${231}`,
      description: "fssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss",
      startTime: 0.77,
      endTime: 0.87,
    }];
    for (let i = 0; i < 100; i++) {
      events.push({
        name: `${i}`,
        description: "asdfe",
        startTime: 0.77,
        endTime: i % 2 === 0 ? null : 0.78,
      });
    }
    return events;
  }


  public createEventOrder(event: EventType) {
    return this._http.postData(`${EVENT_URL}/create`, event);
  }

  public getAllEvents(): Observable<EventType[]> {
    return this._http.getData<EventType[]>(`${EVENT_URL}/all`)
      .pipe(map((r) => r.data));
  }
}
