import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {filter} from "rxjs/operators";
import {MatDialog} from "@angular/material/dialog";
import {ProductCreateComponent} from "../product-create/product-create.component";
import {ProductService} from "../../../service/product.service";
import {FormArray, FormBuilder} from "@angular/forms";
import {AppComponent} from "../../../app.component";
import {ProductType} from "../../../data/types";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-products-view',
  templateUrl: './products-view.component.html',
  styleUrls: ['./products-view.component.scss']
})
export class ProductsViewComponent implements OnInit {
  hidden = false;

  form = this._fb.group(
    {products: this._fb.array([])}
  );
  dataSource: MatTableDataSource<ProductType>;
  displayedColumns = ["name", "description", "owner", "action"];

  public get isEmployee() {
    return AppComponent.user.isEmployee;
  }

  constructor(
    private _cdr: ChangeDetectorRef,
    private _fb: FormBuilder,
    private productService: ProductService,
    private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this._getProducts();
    this._getBasket();
  }

  toggleBadgeVisibility() {
    this.hidden = !this.hidden;
  }

  addProduct() {
    this.dialog.open(ProductCreateComponent, {
      height: '70%',
      width: '40%',
    }).afterClosed()
      .pipe(
        filter((res) => res === 'confirm')
      ).subscribe(() => {
      this._getProducts();
    });
  }

  get products() {
    return this.form.controls["products"] as FormArray;
  }

  public _getProducts() {
    this.productService.getProducts().subscribe((products) => {
      this.products.clear();
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < products.data?.length; i++) {
        this.products.push(this._fb.group(products.data[i]));
      }
      this._cdr.markForCheck();
    });
  }

  addToBasket(index: number, productForm: ProductType) {
    this.products.removeAt(index);
    this.productService.addToBasket({
      productId: productForm.id,
      toUser: AppComponent.user.login,
      fromUser: productForm.owner,
      fromTime: productForm.timeCurrent,
      toTime: productForm.timeCurrent,
    }).subscribe(() => {
      this._getBasket();
    });
  }

  public _getBasket() {
    if (this.isEmployee) { return; }
    return this.productService.getBasket().subscribe((response) => {
      console.log(response);
      this.dataSource = new MatTableDataSource<ProductType>(response);
      this._cdr.markForCheck();
    });
  }

  public getLogin() {
    return AppComponent.user.login;
  }
}
