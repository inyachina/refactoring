import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {EventType} from "../../../data/types";
import {EventService} from "../../../service/event.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

@Component({
  selector: 'app-event-table',
  templateUrl: './event-table.component.html',
  styleUrls: ['./event-table.component.scss']
})
export class EventTableComponent implements OnInit, AfterViewInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  public dataSource: MatTableDataSource<EventType>;
  displayedColumns = ["name",
    "description",
    "startTime",
    "endTime"];
  public value = "";


  constructor(
    private eventService: EventService,
    private _cdr: ChangeDetectorRef,
  ) {
  }

  ngOnInit(): void {
    // this.dataSource = new MatTableDataSource<EventType>(this.eventService._getEvents());
    this.eventService.getAllEvents().subscribe((response) => {
      this.dataSource = new MatTableDataSource<EventType>(response);
      this._cdr.markForCheck();
    });

  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }
}
