import {ChangeDetectorRef, Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {UserService} from "../../../service/user/user.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Router} from "@angular/router";
import {AppComponent} from "../../../app.component";
import {ApiResponse} from "../../../service/http/http.type";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.components.scss']
})
export class LoginComponent implements OnInit {
  @Output() goToRegistrationEvent = new EventEmitter();

  public loginFormGroup = this._fb.group({
    loginControl: [null, Validators.required],
    passwordControl: [null, Validators.required],
  });
  public isHidden = true;

  constructor(
    private userService: UserService,
    private _cdr: ChangeDetectorRef,
    private _router: Router,
    private _snackbar: MatSnackBar,
    private _fb: FormBuilder) {
  }

  ngOnInit(): void {
    // todo remove
    // this.redirectToMainPage();
  }

  public _goToRegistration() {
    this.goToRegistrationEvent.emit();
  }

  public authenticate() {
    AppComponent.user = {
      login: this.loginFormGroup.value.loginControl,
      password: this.loginFormGroup.value.passwordControl,
      isEmployee: false,
    };

    this.userService.authenticate().subscribe((response: ApiResponse<any>) => {
      if (response.isSuccess) {
        AppComponent.user.isEmployee = response.data;
        this.redirectToMainPage();
      } else {
        this._snackbar.open("Неверный логин или пароль", "Закрыть", {duration: 3000});
      }
    });
  }

  private redirectToMainPage() {
    this._router.navigateByUrl("main/store");
    localStorage.setItem("utl", "main/store");
  }
}
