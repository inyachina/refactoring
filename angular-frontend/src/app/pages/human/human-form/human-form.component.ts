import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, Validators} from "@angular/forms";
import {HumanService} from "../../../service/human.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ignoreDiagnostics} from "@angular/compiler-cli/src/ngtsc/typecheck/src/diagnostics";

@Component({
  selector: 'app-human-form',
  templateUrl: './human-form.component.html',
  styleUrls: ['./human-form.component.scss']
})
export class HumanFormComponent implements OnInit {
  humanForm = this._fb.group({
      name: [null, Validators.required],
      surname: [null, Validators.required],
      birthdayDate: [null, Validators.required],
      time: [null, Validators.required],
    }
  );

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<HumanFormComponent>,
    public humanService: HumanService,
    public _fb: FormBuilder,
    private _snackBar: MatSnackBar,
  ) {
  }


  ngOnInit(): void {
  }

  onClose() {
    let human = this.humanForm.getRawValue();
    human.time = human.time / 100;
    this.humanService.createHuman(human).subscribe((response) => {
      if (response.isSuccess) {
        this._snackBar.open('Человек добавлен', 'Закрыть', {
          duration: 3000,
        });
      }
      this.dialogRef.close('confirm');
    });
  }
}
