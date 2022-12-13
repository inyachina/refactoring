import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {EventService} from "../../../service/event.service";
import {MatTableDataSource} from "@angular/material/table";
import {EventType, OrderEventType} from "../../../data/types";
import {OrderService} from "../../../service/order.service";

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.scss']
})
export class EventFormComponent implements OnInit {
  public formGroup = this._fb.group(
    {
      name: [null, Validators.required],
      startTime: [null, Validators.required],
      endTime: null,
      description: [null, Validators.required],
    }
  );
  public dataSource: MatTableDataSource<OrderEventType>;
  public displayedColumnsOrders = ["orderId", "orderStatus"];

  constructor(
    private eventService: EventService,
    private orderService: OrderService,
    private _fb: FormBuilder,
    private _cdr: ChangeDetectorRef,
  ) {
  }

  ngOnInit(): void {
    this.getOrders();
  }


  public getOrders() {
    this.orderService.getAllEventOrders().subscribe((response) => {
      this._cdr.markForCheck();
      // this.dataSource = new MatTableDataSource<OrderEventType>
    });
  }

  public createEventOrder() {
    const event: EventType = this.formGroup.getRawValue();
    this.eventService.createEventOrder(event).subscribe((response) => {
      this.formGroup = this._fb.group(
        {
          name: [null, Validators.required],
          startTime: [null, Validators.required],
          endTime: null,
          description: [null, Validators.required],
        }
      );
      this.getOrders();
    });
  }
}
