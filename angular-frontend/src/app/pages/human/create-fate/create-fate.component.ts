import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {MatSnackBar} from "@angular/material/snack-bar";
import {OrderService} from "../../../service/order.service";
import {FormControl, Validators} from "@angular/forms";

@Component({
  selector: 'app-create-fate',
  templateUrl: './create-fate.component.html',
  styleUrls: ['./create-fate.component.scss']
})
export class CreateFateComponent implements OnInit {

  public isInProcess = false;
  fate = new FormControl(null, Validators.required);

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
    public dialogRef: MatDialogRef<CreateFateComponent>,
    private _snackbar: MatSnackBar,
    private _cdr: ChangeDetectorRef,
    private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.orderService.getHumanFate(this.data.id).subscribe((response) => {
      if (response.data) {
        this.isInProcess = true;
      }

    });
  }

  changeHumanFate() {
    this.orderService.changeHumanFate(this.data.id, this.fate.value).toPromise();
    this.dialogRef.close('confirm');
  }

}
