import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AppComponent} from "../app.component";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {
  showFiller = false;

  constructor(private _router: Router) {
  }

  ngOnInit(): void {
  }
  public logout() {
    this._router.navigateByUrl('login');
    localStorage.setItem("url", null);
    AppComponent.user = null;
  }

  public goToProduct() {
    this._router.navigateByUrl('main/store');
    localStorage.setItem("url", 'main/store');
  }

  public goToEvent() {
    this._router.navigateByUrl('main/events');
    localStorage.setItem("url", 'main/events');
  }

  public goToPeople() {
    this._router.navigateByUrl('main/people');
    localStorage.setItem("url", 'main/people');
  }
}
