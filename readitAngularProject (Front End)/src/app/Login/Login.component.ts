import { Component, OnInit } from '@angular/core';
import { AuthService } from '../Service/auth.service';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UserDTO } from '../Models/UserDTO';
import { UserService } from '../Service/user.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

//return sessionStorage.getItem(TOKEN_KEY);            GET TOKEN
//return JSON.parse(sessionStorage.getItem(USER_KEY)); GET USER

export class LoginComponent implements OnInit {

  loginFailed: boolean;
  errorMessage = '';
  username: string;
  password: string;
  hide = true;
  usernameField: FormControl;
  passwordField: FormControl;
  userReceived: UserDTO;
  
  constructor(private router: Router, private authService: AuthService, private userService: UserService) {
  }

  ngOnInit(): void {
    if (sessionStorage.getItem(TOKEN_KEY)) {  // if already login, redirect to home page
      this.router.navigate(['Index']);
    }
    this.loginFailed = false;
    this.usernameField = new FormControl('', [Validators.required]);
    this.passwordField = new FormControl('', [Validators.required]);
  }

  login() {
    this.authService.authenticate(this.username, this.password).subscribe({
      next: (response) => {                                       // Login successful
        console.log("Login successful")
        var token = response.headers.get('authorization');
        var user = response.headers.get('username');

        window.sessionStorage.removeItem(TOKEN_KEY);
        window.sessionStorage.removeItem(USER_KEY);
        window.sessionStorage.setItem(TOKEN_KEY, token);

        this.userService.findByUsername(user).subscribe({
          next: (response) => {
            this.userReceived = response.body;
            window.sessionStorage.setItem(USER_KEY, JSON.stringify(this.userReceived));
          },
          error: (err) => {

          }
        });
        //
        this.loginFailed = false;
        this.router.navigate(['Index']);
      },
      error: (err) => {                                             // Login failed
        console.log("Login failed");
        //console.log(err);
        this.loginFailed = true;
        this.getErrorMessageUsername()
      }
    });
  }

  getErrorMessageUsername() {
    if (this.usernameField.hasError('required')) {
      return 'You must enter a username';
    }
    if (this.loginFailed) {
      return 'Wrong username or password';
    }
  }

  getErrorMessagePassword() {
    if (this.passwordField.hasError('required')) {
      return 'You must enter a password';
    }
  }
}
