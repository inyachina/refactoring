import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFateComponent } from './create-fate.component';

describe('CreateFateComponent', () => {
  let component: CreateFateComponent;
  let fixture: ComponentFixture<CreateFateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
