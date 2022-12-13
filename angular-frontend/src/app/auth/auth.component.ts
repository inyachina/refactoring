import {ChangeDetectorRef, Component, OnDestroy, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {animate, state, style, transition, trigger} from "@angular/animations";

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss'],
  animations: [
    trigger('loginBlock', [
      state('visible', style({opacity: 1, display: "block"})),
      state('invisible', style({opacity: 0, display: "none"})),
      transition('visible <=> invisible', animate('0.1s')),
    ]),
    trigger('registrationBlock', [
      state('visible', style({opacity: 1})),
      state('invisible', style({opacity: 0})),
      transition('visible <=> invisible', animate('0.2s')),
    ])
  ]
})
export class AuthComponent implements OnInit, OnDestroy {
  public isRegistration = false;

  constructor(
    private _router: Router,
    private _cdr: ChangeDetectorRef,
  ) {
  }

  public ngOnInit(): void {
  }

  private _redirect(): void {
    this._router.navigateByUrl('main');
  }

  ngOnDestroy(): void {
    localStorage.setItem("url", null);
  }
}
