import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentSignUpComponent } from './agent-sign-up.component';

describe('AgentSignUpComponent', () => {
  let component: AgentSignUpComponent;
  let fixture: ComponentFixture<AgentSignUpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgentSignUpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentSignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
