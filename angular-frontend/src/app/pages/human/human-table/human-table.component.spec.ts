import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HumanTableComponent } from './human-table.component';

describe('HumanTableComponent', () => {
  let component: HumanTableComponent;
  let fixture: ComponentFixture<HumanTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HumanTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HumanTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
