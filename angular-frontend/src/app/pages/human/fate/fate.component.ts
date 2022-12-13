import {ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {OrderService} from "../../../service/order.service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-fate',
  templateUrl: './fate.component.html',
  styleUrls: ['./fate.component.scss']
})
export class FateComponent implements OnInit {

  public isInProcess = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: { id: number },
    public dialogRef: MatDialogRef<FateComponent>,
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

  countFate() {
    this.orderService.createHumanOrder(this.data.id).subscribe((response) => {
      this.dialogRef.close();
      this._snackbar.open('Заявка на расчет судьбы сформирована', 'Закрыть', {
        duration: 3000,
      });
    });
  }
}
