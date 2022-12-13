import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {HumanType} from "../../../data/types";
import {MatDialog} from '@angular/material/dialog';
import {HumanFormComponent} from "../human-form/human-form.component";
import {filter} from "rxjs/operators";
import {HumanService} from "../../../service/human.service";
import {FateComponent} from "../fate/fate.component";
import {AppComponent} from "../../../app.component";
import {CreateFateComponent} from "../create-fate/create-fate.component";

@Component({
  selector: 'app-human-table',
  templateUrl: './human-table.component.html',
  styleUrls: ['./human-table.component.scss']
})
export class HumanTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  public dataSource: MatTableDataSource<HumanType>;
  displayedColumns = [
    "humanId",
    "name",
    "surname",
    "birthdayDate",
    "time",
    "fate"];
  public value = "";

  constructor(private dialog: MatDialog,
              private humanService: HumanService,
              private _cdr: ChangeDetectorRef,
  ) {
  }

  ngOnInit(): void {
    this._getPeople();
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  public get isEmployee() {
    return AppComponent.user.isEmployee;
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  public createHuman() {
    this.dialog.open(HumanFormComponent, {
      height: '70%',
    }).afterClosed()
      .pipe(
        filter((res) => res === 'confirm')
      ).subscribe(() => {
      this._getPeople();
    });
  }

  public _getPeople() {
    if (!this.isEmployee) {
      this.humanService.getPeople().subscribe((people) => {
        this.dataSource = new MatTableDataSource<HumanType>(people);
        this._cdr.markForCheck();
      });
    }
    else {
      this.humanService.getHumanOrders().subscribe((people) => {
        this.dataSource = new MatTableDataSource<HumanType>(people);
        this._cdr.markForCheck();
      });
    }
  }

  moreInfo(id: number) {
    this.dialog.open(FateComponent,
      {
        height: '40%',
        data: {id}
      }).afterClosed()
      .pipe(
        filter((res) => res === 'confirm')
      ).subscribe(() => {
      this._getPeople();
    });
  }

  setFate(id) {
    this.dialog.open(CreateFateComponent,
      {
        height: '50%',
        width: '40%',
        data: {id}
      }).afterClosed()
      .pipe(
        filter((res) => res === 'confirm')
      ).subscribe(() => {
      this._getPeople();
    });
  }
}
