import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {STEPPER_GLOBAL_OPTIONS} from "@angular/cdk/stepper";
import { regexPhone} from "../../../data/regex";
import {AuthorizationRequest} from "../../../service/user/userType";
import {UserService} from "../../../service/user/user.service";
import {AppComponent} from "../../../app.component";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS,
      useValue: {showError: true},
    },
  ],
})
export class RegistrationComponent implements OnInit {
  @Output() goToLoginEvent = new EventEmitter();
  public firstFormGroup = this._fb.group({
    login: ['', Validators.required],
  });
  public secondFormGroup = this._fb.group({
    password: ['', Validators.minLength(3)],
  });

  public thirdFormGroup = this._fb.group({
    email: ['', Validators.required],
    phone: ['',  Validators.pattern(regexPhone)]
  });

  public isEmployeeControl = new FormControl(false);

  public isHiddenPassword = true;

  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _snackbar: MatSnackBar,
    private userService: UserService,
  ) {
  }

  ngOnInit(): void {
  }

  public get areValidForms(){
    return this.firstFormGroup.valid && this.secondFormGroup.valid && this.thirdFormGroup.valid;
  }

  public _goToLogin() {
    this.goToLoginEvent.emit();
  }

  public authorize() {
    const authorizationRequest: AuthorizationRequest = {
      login: this.firstFormGroup.value.login,
      password: (this.secondFormGroup.value.password),
      email: this.thirdFormGroup.value.email,
      phone: this.thirdFormGroup.value.phone,
      isEmployee: this.isEmployeeControl.value,
    };
    AppComponent.user = {login: authorizationRequest.login, password: authorizationRequest.password, isEmployee: this.isEmployeeControl.value};
    this.userService.authorizate(authorizationRequest).subscribe((response) => {
    if (response.isSuccess) {
      this.redirectToMainPage();
    } else {
        this._snackbar.open("Указанный логин занят", "Закрыть", {duration: 3000});
    }
  });
  }

  private redirectToMainPage() {
    this._router.navigateByUrl("main/events");
  }
}
