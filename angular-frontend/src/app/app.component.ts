import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from "./service/user/userType";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  // todo remove
  static user: User = null;
  // static user: User = {login: "111", password: "111", isEmployee: false};
  constructor(
    private _router: Router,
  ) {
  }

  public ngOnInit(): void {
    this._redirect();
    if (AppComponent.user?.login) {
      this._navigateToMain();
    }
    this._navigateToAuth();
  }


  private _redirect(): void {
    const redirect = '?redierect=';

    if (window.location.search.includes(redirect)) {
      const path = window.location.search.replace(redirect, '');

      if (path) {
        this._router.navigateByUrl(path);
      }
    }
  }

  private _navigateToAuth(): void {
    if (this._router.url.indexOf('/login/') === -1) {
      this._router.navigateByUrl('login');
    }
  }

  private _navigateToMain() {
    this._router.navigateByUrl('main');
  }
}
