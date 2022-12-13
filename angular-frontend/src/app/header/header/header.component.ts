import {Component, OnInit, ViewChild} from '@angular/core';
import {MatMenuTrigger} from "@angular/material/menu";
import {Router} from "@angular/router";
import {AppComponent} from "../../app.component";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  @ViewChild(MatMenuTrigger) trigger: MatMenuTrigger;
  constructor(private _router: Router) {

  }

  ngOnInit(): void {
  }

  public logout() {
    AppComponent.user = null;
    this._router.navigateByUrl('login');
  }

  public goToProduct() {
    this._router.navigateByUrl('main/products');
  }

  public goToEvent() {
    this._router.navigateByUrl('main/events');
  }
  // logout() {
  //
  // }
}
