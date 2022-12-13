import {Injectable} from '@angular/core';
import {HttpService} from "./http/http.service";
import {ProductOrderType, ProductType} from "../data/types";
import {ORDER_URL, PRODUCT_URL} from "../data/server_urls";
import {AppComponent} from "../app.component";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpService,
  ) {
  }

  createProduct(product: ProductType) {
    return this.http.postData(`${PRODUCT_URL}/create`, product);
  }

  getProducts() {
    return this.http.getData<ProductType[]>(`${PRODUCT_URL}/all`);
  }

  addToBasket(order: ProductOrderType) {
    return this.http.postData(`${ORDER_URL}/product/create/`, order);
  }

  removeFromBasket() {
    return this.http.deleteData(`${ORDER_URL}/product/create/`);
  }
  getBasket() {
    return this.http.getData<ProductType[]>(`${ORDER_URL}/product/basket/${AppComponent.user.login}`)
      .pipe(map((r) => r.data));
  }
}
