import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MultiUserChatComponent } from './multi-user-chat/multi-user-chat.component';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './Login/Login.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { AuthService } from './Service/auth.service';
import { PostComponent } from './post/post.component';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import { RegisterComponent } from './register/register.component';
import { PostService } from './Service/post.service';
import { IndexComponent } from './index/index.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UserService } from './Service/user.service';
import { CreatePostComponent } from './create-post/create-post.component';
import { CommentService } from './Service/comment.service';
import { UpvoteDownvoteService } from './Service/upvote-downvote.service';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatSidenavModule} from '@angular/material/sidenav';
import { UserProfileComponent } from './user-profile/user-profile.component';

@NgModule({
  declarations: [
    AppComponent,
    MultiUserChatComponent,
    LoginComponent,
    PostComponent,
    RegisterComponent,
    IndexComponent,
    NavbarComponent,
    CreatePostComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MatIconModule,
    MatInputModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatSlideToggleModule,
    MatSidenavModule
  ],
  providers: [AuthService, PostService, UserService, CommentService, UpvoteDownvoteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
