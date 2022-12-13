import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AuthComponent} from './auth/auth.component';
import {MainPageComponent} from "./pages/main-page/main-page.component";
import {EventTableComponent} from "./pages/event-page/event-table/event-table.component";
import {HumanTableComponent} from "./pages/human/human-table/human-table.component";
import {ProductsViewComponent} from "./pages/product-page/products-view/products-view.component";

const routes: Routes = [
  {path: 'login', component: AuthComponent},
  // {path: 'main', component: MainPageComponent},
  {path: 'main/events', component: EventTableComponent},
  {path: 'main/store', component: ProductsViewComponent},
  {path: 'main/people', component: HumanTableComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
