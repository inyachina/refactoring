import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {MainPageComponent} from "./pages/main-page/main-page.component";

const routes: Routes = [
  {path: 'login', component: AuthComponent},
  {path: 'main', component: MainPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
