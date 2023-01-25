import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { subscribeOn } from 'rxjs';
import { DownvoteDTO } from '../Models/DownvoteDTO';
import { PostDTO } from '../Models/PostDTO';
import { UpvoteDTO } from '../Models/UpvoteDTO';
import { UserDTO } from '../Models/UserDTO';
import { PostService } from '../Service/post.service';
import { UpvoteDownvoteService } from '../Service/upvote-downvote.service';
import { UserService } from '../Service/user.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  postFilter: string;
  upvoteDTO: UpvoteDTO;
  downvoteDTO: DownvoteDTO;
  postDTO: PostDTO;
  postToChangeDTO: PostDTO;
  userId: number;

  constructor(private router: Router, public postService: PostService, public userService: UserService, public upvoteDownvoteService: UpvoteDownvoteService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    if (!sessionStorage.getItem(TOKEN_KEY)) {
      this.router.navigate(['Login']);                               // if not logged in, redirect to login
    }
    this.userId = this.route.snapshot.params['userId'];
  }

  ngAfterViewInit() {
    this.filterByDates();
    this.fetchUpvotes();
    this.fetchDownvotes();
  }

  fetchPosts() {
    if (this.userId == null) {
      this.postService.getPosts()
        .subscribe((res) => {
          this.postService.posts = res.body as PostDTO[];
          if (this.postFilter == "vote") {
            this.postService.posts.sort((a, b) => b.number_upvotes - a.number_upvotes);
          }
          else {
            this.postService.posts.sort((a, b) => new Date(b.publicationDate).getTime() - new Date(a.publicationDate).getTime());
          }
        });
    }
    else {
      this.postService.getPostsByUserId(this.userId)
        .subscribe((res) => {
          this.postService.posts = res.body as PostDTO[];
          if (this.postFilter == "vote") {
            this.postService.posts.sort((a, b) => b.number_upvotes - a.number_upvotes);
          }
          else {
            this.postService.posts.sort((a, b) => new Date(b.publicationDate).getTime() - new Date(a.publicationDate).getTime());
          }
        });
    }
  }

  filterByDates() {
    this.postFilter = "date";
    this.fetchPosts();
  }

  filterByVotes() {
    this.postFilter = "vote";
    this.fetchPosts();
  }

  fetchUpvotes() {
    this.upvoteDownvoteService.getUpvoteByUserId(this.userService.getCurrentUser()?.id).subscribe((res) => {
      this.upvoteDownvoteService.currentUserUpvotes = res.body as UpvoteDTO[];
    });
  }

  fetchDownvotes() {
    this.upvoteDownvoteService.getDownvoteByUserId(this.userService.getCurrentUser()?.id).subscribe((res) => {
      this.upvoteDownvoteService.currentUserDownvotes = res.body as DownvoteDTO[];
    });
  }

  upvoteContains(postId) {
    if (this.upvoteDownvoteService.currentUserUpvotes?.some(obj => obj.post.id == postId)) {
      return true;
    }
    return false;
  }

  downvoteContains(postId) {
    if (this.upvoteDownvoteService.currentUserDownvotes?.some(obj => obj.post.id == postId)) {
      return true;
    }
    return false;
  }

  upvotePost(postId) {
    if (this.upvoteContains(postId)) {           // If the user has already upvoted we remove the upvote, else we upvote
      this.upvoteDTO = this.upvoteDownvoteService.currentUserUpvotes.find(upvote => upvote.post.id == postId && upvote.user.id == this.userService.getCurrentUser()?.id);
      this.upvoteDownvoteService.deleteUpvote(this.upvoteDTO.id).subscribe((data) => {
        console.log("delete success");
        this.fetchUpvotes();
        this.fetchDownvotes();
        this.updateNumberUpvote(-1, postId);
      });
    }
    else {
      this.postService.getPostsById(postId).subscribe({
        next: (response) => {
          this.upvoteDTO = new UpvoteDTO();
          this.upvoteDTO.post = response.body as PostDTO;
          this.upvoteDTO.user = this.userService.getCurrentUser();
          this.upvoteDownvoteService.createUpvote(this.upvoteDTO).subscribe({
            next: (response) => {                                       // create successful
              console.log("Create upvote successful");
              if (this.downvoteContains(postId)) {                      // if the post was previously downvoted, we have to remove it from downvotes
                this.downvoteDTO = this.upvoteDownvoteService.currentUserDownvotes.find(downvote => downvote.post.id == postId && downvote.user.id == this.userService.getCurrentUser()?.id);
                this.upvoteDownvoteService.deleteDownvote(this.downvoteDTO.id).subscribe((data) => {
                  console.log("delete success");
                  this.fetchUpvotes();
                  this.fetchDownvotes();
                  this.updateNumberUpvote(2, postId);
                });
              }
              else {
                this.fetchUpvotes();
                this.fetchDownvotes();
                this.updateNumberUpvote(1, postId);
              }
            },
            error: (err) => {                                           // create failed
              console.log("Create upvote failed");
              //console.log(err);
            }
          });
        },
        error: (err) => {
          console.log("Get post failed");
        }
      });
    }
  }

  downvotePost(postId) {
    if (this.downvoteContains(postId)) {           // If the user has already downvoted we remove the downvote, else we downvote
      this.downvoteDTO = this.upvoteDownvoteService.currentUserDownvotes.find(downvote => downvote.post.id == postId && downvote.user.id == this.userService.getCurrentUser()?.id);
      this.upvoteDownvoteService.deleteDownvote(this.downvoteDTO.id).subscribe((data) => {
        console.log("delete success");
        this.fetchUpvotes();
        this.fetchDownvotes();
        this.updateNumberUpvote(1, postId);
      });
    }
    else {
      this.postService.getPostsById(postId).subscribe({
        next: (response) => {
          this.downvoteDTO = new DownvoteDTO();
          this.downvoteDTO.post = response.body as PostDTO;
          this.downvoteDTO.user = this.userService.getCurrentUser();
          this.upvoteDownvoteService.createDownvote(this.downvoteDTO).subscribe({
            next: (response) => {                                       // create successful
              console.log("Create downvote successful");
              if (this.upvoteContains(postId)) {
                this.upvoteDTO = this.upvoteDownvoteService.currentUserUpvotes.find(upvote => upvote.post.id == postId && upvote.user.id == this.userService.getCurrentUser()?.id);
                this.upvoteDownvoteService.deleteUpvote(this.upvoteDTO.id).subscribe((data) => {
                  console.log("delete success");
                  this.fetchUpvotes();
                  this.fetchDownvotes();
                  this.updateNumberUpvote(-2, postId);
                });                                // if the post was previously upvoted, we have to remove it from upvotes
              }
              else {
                this.fetchUpvotes();
                this.fetchDownvotes();
                this.updateNumberUpvote(-1, postId);
              }
            },
            error: (err) => {                                           // create failed
              console.log("Create downvote failed");
              //console.log(err);
            }
          });
        },
        error: (err) => {
          console.log("Get post failed");
        }
      });
    }
  }

  updateNumberUpvote(numChange, postId) {
    this.postService.getPostsById(postId).subscribe({
      next: (response) => {
        this.postToChangeDTO = response.body as PostDTO;
        this.postToChangeDTO.number_upvotes += numChange;
        this.postService.updatePost(this.postToChangeDTO, postId).subscribe({
          next: (response) => {
            console.log("Update post successful");
            this.fetchPosts();
          },
          error: (err) => {
            console.log("Update post failed");
            //console.log(err);
          }
        })
      },
      error: (err) => {
        console.log("Get post failed");
      }
    });
  }

  //TODO DELETE ALL UPVOTES/DOWNVOTES ASSOCIATED WITH THIS POST
  deletePost(postId) {
    this.postService.deletePost(postId).subscribe((data) => {
      console.log("delete success");
      this.fetchPosts();
    });
  }

}
