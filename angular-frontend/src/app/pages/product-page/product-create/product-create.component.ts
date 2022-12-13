import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ProductService} from "../../../service/product.service";

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.scss']
})
export class ProductCreateComponent implements OnInit {
  productForm = this._fb.group({
    name: [null, Validators.required],
    description: [null, Validators.required],
    timeCurrent: [null, Validators.required],
  });

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ProductCreateComponent>,
              public productService: ProductService,
              public _fb: FormBuilder,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
  }

  onClose() {
    this.productService.createProduct(this.productForm.getRawValue()).subscribe((response) => {
      this.dialogRef.close('confirm');
      // @ts-ignore
      if (response.isSuccess) {
        this._snackBar.open('Товар добавлен', 'Закрыть', {
          duration: 3000,
        });
      }
    });
  }
}
