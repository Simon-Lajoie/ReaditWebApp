import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PostDTO } from '../Models/PostDTO';
import { UserDTO } from '../Models/UserDTO';
import { AuthService } from '../Service/auth.service';
import { PostService } from '../Service/post.service';
import { formatDate } from "@angular/common";
import { UserService } from '../Service/user.service';
import { CommentDTO } from '../Models/CommentDTO';
import { CommentService } from '../Service/comment.service';
import { UpvoteDownvoteService } from '../Service/upvote-downvote.service';
import { UpvoteDTO } from '../Models/UpvoteDTO';
import { DownvoteDTO } from '../Models/DownvoteDTO';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const UPVOTE_AMOUNT = 1;
const DOWNVOTE_AMOUNT = -1;

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})

export class PostComponent implements OnInit {

  private sub: any;
  postId: number;
  currentPost: PostDTO;
  comments: CommentDTO[];
  commentDTO: CommentDTO;
  commentInput: string;
  upvoteDTO: UpvoteDTO;
  downvoteDTO: DownvoteDTO
  postToChangeDTO: PostDTO;

  constructor(private router: Router, public userService: UserService, public postService: PostService, private route: ActivatedRoute, public commentService: CommentService, public upvoteDownvoteService: UpvoteDownvoteService) { }

  ngOnInit() {
    if (!sessionStorage.getItem(TOKEN_KEY)) {
      this.router.navigate(['Login']);                               // if not logged in, redirect to login
    }
    this.postId = parseInt(this.route.snapshot.params['postId']);
    console.log("THIS IS THE POST ID: " + this.postId);
    this.fetchCurrentPost();
    this.fetchComments();
  }

  getCurrentPost(): PostDTO {
    return this.currentPost;
  }

  fetchCurrentPost() {
    this.postService.getPostsById(this.postId)
      .subscribe((res) => {
        this.currentPost = res.body as PostDTO;
      });
  }

  fetchComments() {
    this.commentService.findAllCommentsByPostId(this.postId)
      .subscribe((res) => {
        this.comments = res.body as CommentDTO[];
      })
  }

  createComment() {
    this.postService.getPostsById(this.getCurrentPost().id).subscribe({
      next: (response) => {
        console.log("Get current post successful");
        this.commentDTO = new CommentDTO();
        this.commentDTO.post = response.body as PostDTO;
        this.commentDTO.description = this.commentInput;
        this.commentDTO.publicationDate = new Date();
        this.commentDTO.user = JSON.parse(sessionStorage.getItem(USER_KEY));
        console.log(this.commentDTO);
        this.commentService.createComment(this.commentDTO).subscribe({
          next: (response) => {                                       // create successful
            console.log("Create comment successful");
            this.fetchComments();
            this.commentInput = "";
          },
          error: (err) => {                                           // create failed
            console.log("Create comment failed");
            //console.log(err);
          }
        });
      },
      error: (err) => {
        console.log("Get current post failed");
      }
    });
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
        this.updateNumberUpvote(DOWNVOTE_AMOUNT, postId);
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
              this.fetchUpvotes();
              this.fetchDownvotes();
              this.updateNumberUpvote(UPVOTE_AMOUNT, postId);
              if (this.downvoteContains(postId)) {
                this.downvotePost(postId);                              // if the post was previously downvoted, we have to remove it from downvotes
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
      console.log("THIS WAS ALREADY DOWNVOTED !!! +1");
      this.downvoteDTO = this.upvoteDownvoteService.currentUserDownvotes.find(downvote => downvote.post.id == postId && downvote.user.id == this.userService.getCurrentUser()?.id);
      this.upvoteDownvoteService.deleteDownvote(this.downvoteDTO.id).subscribe((data) => {
        console.log("delete success");
        this.fetchUpvotes();
        this.fetchDownvotes();
        this.updateNumberUpvote(UPVOTE_AMOUNT, postId);
      });
    }
    else {
      this.postService.getPostsById(postId).subscribe({
        next: (response) => {
          console.log("THIS WAS NOT ALREADY DOWNVOTED ! -1");
          this.downvoteDTO = new DownvoteDTO();
          this.downvoteDTO.post = response.body as PostDTO;
          this.downvoteDTO.user = this.userService.getCurrentUser();
          this.upvoteDownvoteService.createDownvote(this.downvoteDTO).subscribe({
            next: (response) => {                                       // create successful
              console.log("Create downvote successful");
              this.fetchUpvotes();
              this.fetchDownvotes();
              console.log(this.upvoteDownvoteService.currentUserDownvotes);
              this.updateNumberUpvote(DOWNVOTE_AMOUNT, postId);
              if (this.upvoteContains(postId)) {
                console.log("THIS WAS NOT ALREADY DOWNVOTED AND ALREADY UPVOTED -2");
                this.upvotePost(postId);                                // if the post was previously upvoted, we have to remove it from upvotes
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
            this.fetchCurrentPost();
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

  deleteComment(commentId) {
    this.commentService.deleteComment(commentId).subscribe((data) => {
      console.log("delete success");
      this.fetchComments();
    });
  }
}
