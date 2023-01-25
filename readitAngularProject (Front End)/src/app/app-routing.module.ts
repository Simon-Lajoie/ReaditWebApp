import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePostComponent } from './create-post/create-post.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './Login/Login.component';
import { MultiUserChatComponent } from './multi-user-chat/multi-user-chat.component';
import { PostComponent } from './post/post.component';
import { RegisterComponent } from './register/register.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'Login', component: LoginComponent },
  { path: 'Register', component: RegisterComponent },
  { path: 'Post/:postId', component: PostComponent },
  { path: 'Index/:userId', component: IndexComponent },
  { path: 'Index', component: IndexComponent },
  { path: 'CreatePost', component: CreatePostComponent },
  { path: 'MultiChat', component: MultiUserChatComponent },
  { path: 'Profile', component: UserProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
