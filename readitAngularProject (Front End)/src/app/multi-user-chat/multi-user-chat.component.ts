import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { ChatMessageDTO } from '../Models/ChatMessageDTO';
import { UserDTO } from '../Models/UserDTO';
import { UserService } from '../Service/user.service';
import { WebSocketService } from '../Service/websocket.service';

@Component({
  selector: 'app-multi-user-chat',
  templateUrl: './multi-user-chat.component.html',
  styleUrls: ['./multi-user-chat.component.css']
})
export class MultiUserChatComponent {
  onlineUsers: UserDTO[] = [];
  chatMessages: any[] = [];
  broadcastMessages: string[] = [];
  chatMessage: any;
  message: string;
  parsedMessage: string;
  private webSocketSubscription: Subscription;
  private username: string;
  private user: UserDTO;
  private intervalId: any;
  constructor(private router: Router, private webSocketService: WebSocketService, private userService: UserService) {
  }

  ngOnInit() {
    // Get the current user's username
    const username = this.userService.getCurrentUser().username;

    // Connect to the WebSocket endpoint
    this.webSocketSubscription = this.webSocketService.connect(`ws://localhost:8080/chat?username=${username}`).subscribe({
      next: (message) => {
        //this.parsedMessage = message.message.replace(/^"(.*)"$/, '$1');
        // if username is null, its a broadcast message indicating a user has joined or left
        if (message.username == null) {
          this.broadcastMessages.push(message.message);
          console.log(this.broadcastMessages);
        }
        // else it is a message from a user
        else {
          this.chatMessages.push({
            username: message.username,
            message: message.message
          });
          console.log(this.chatMessages);
        }
      },
      error: (error) => {
        console.log('WebSocket error: ', error);
      }
    });


    // Periodically check for new online users
    this.intervalId = setInterval(() => {
      this.userService.findAllOnlineUsers().subscribe({
        next: (response) => {
          this.onlineUsers = response.body;
        },
        error: (error) => {
          console.log('Error finding online users: ', error);
        }
      });
    }, 1000);
  }

  sendMessage(message: string) {
    this.webSocketService.send(message);
    this.message = "";
  }

  ngOnDestroy() {
    // Unsubscribe from the WebSocket connection when the component is destroyed
    this.webSocketSubscription.unsubscribe();

    //clear the interval
    clearInterval(this.intervalId);
  }

  leaveChat() {
    this.router.navigate(['Index']);
  }
}


