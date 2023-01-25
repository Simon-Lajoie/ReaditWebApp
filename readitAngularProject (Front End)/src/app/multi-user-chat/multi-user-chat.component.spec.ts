import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MultiUserChatComponent } from './multi-user-chat.component';

describe('MultiUserChatComponent', () => {
  let component: MultiUserChatComponent;
  let fixture: ComponentFixture<MultiUserChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MultiUserChatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MultiUserChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
