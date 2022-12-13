import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthComponent} from './auth/auth.component';
import {LoginComponent} from './auth/login/login/login.component';
import {RegistrationComponent} from './auth/registration/registration/registration.component';
import {HeaderComponent} from './header/header/header.component';
import {MatButtonModule} from "@angular/material/button";
import {MatStepperModule} from "@angular/material/stepper";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {MatCheckboxModule} from "@angular/material/checkbox";
import { MatSnackBarModule} from "@angular/material/snack-bar";
import { MainPageComponent } from './pages/main-page/main-page.component';
import {MatMenuModule} from "@angular/material/menu";
import { EventTableComponent } from './pages/event-page/event-table/event-table.component';
import { EventFormComponent } from './pages/event-page/event-form/event-form.component';
import {MatTableModule} from "@angular/material/table";
import {MatSortModule} from "@angular/material/sort";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTabsModule} from "@angular/material/tabs";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatCardModule} from "@angular/material/card";
import {MatDividerModule} from "@angular/material/divider";
import { SidenavComponent } from './sidenav/sidenav.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import { HumanFormComponent } from './pages/human/human-form/human-form.component';
import { HumanTableComponent } from './pages/human/human-table/human-table.component';
import { ProductsFormComponent } from './pages/product-page/products-form/products-form.component';
import { ProductsViewComponent } from './pages/product-page/products-view/products-view.component';
import {MatDialogModule} from "@angular/material/dialog";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {DateAdapter, MatNativeDateModule} from "@angular/material/core";
import {MatTooltipModule} from "@angular/material/tooltip";
import { BasketComponent } from './pages/product-page/basket/basket.component';
import {MatBadgeModule} from "@angular/material/badge";
import { FateComponent } from './pages/human/fate/fate.component';
import { ProductCreateComponent } from './pages/product-page/product-create/product-create.component';
import {MatProgressBarModule} from "@angular/material/progress-bar";
import { CreateFateComponent } from './pages/human/create-fate/create-fate.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LoginComponent,
    RegistrationComponent,
    HeaderComponent,
    MainPageComponent,
    EventTableComponent,
    EventFormComponent,
    SidenavComponent,
    HumanFormComponent,
    HumanTableComponent,
    ProductsFormComponent,
    ProductsViewComponent,
    BasketComponent,
    FateComponent,
    ProductCreateComponent,
    CreateFateComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        MatInputModule,
        MatIconModule,
        MatButtonModule,
        MatStepperModule,
        MatCheckboxModule,
        MatSnackBarModule,
        MatMenuModule,
        MatTableModule,
        MatSortModule,
        MatPaginatorModule,
        MatTabsModule,
        MatGridListModule,
        MatCardModule,
        MatDividerModule,
        MatSidenavModule,
        MatDialogModule,
        MatDatepickerModule,
        MatNativeDateModule,
        MatTooltipModule,
        MatBadgeModule,
        MatProgressBarModule,
    ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule {}
