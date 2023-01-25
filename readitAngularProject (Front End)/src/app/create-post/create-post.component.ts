import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PostDTO } from '../Models/PostDTO';
import { PostService } from '../Service/post.service';
import { UserService } from '../Service/user.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {

  title: string;
  description: string;
  link: string;
  postDTO: PostDTO;
  createdPost: PostDTO;
  constructor(private router: Router, public postService: PostService, public userService: UserService) { }

  ngOnInit(): void {

  }

  createPost() {
    this.postDTO = new PostDTO();
    this.postDTO.title = this.title;
    this.postDTO.description = this.description;
    this.postDTO.link = this.link;
    this.postDTO.publicationDate = new Date();
    this.postDTO.user = this.userService.getCurrentUser();
    this.postDTO.number_upvotes = 0;
    this.postService.createPost(this.postDTO).subscribe({
      next: (response) => {                                       // create successful
        console.log("Create post successful");
        this.createdPost = response.body as PostDTO;
        this.router.navigate(['Post', this.createdPost.id]);
      },
      error: (err) => {                                           // create failed
        console.log("Create post failed");
        //console.log(err);
      }
    });
  }

}
