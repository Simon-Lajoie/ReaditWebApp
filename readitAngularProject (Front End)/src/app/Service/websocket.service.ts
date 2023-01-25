import { Injectable } from '@angular/core';
import { webSocket } from 'rxjs/webSocket';
import { Subject } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private subject: Subject<any>;

  constructor(private userService: UserService) {}

  connect(url: string): Subject<any> {
    if (!this.subject) {
      this.subject = webSocket(url);
    }
    return this.subject;
  }

  send(data: any) {
    this.subject.next(data);
  }
}