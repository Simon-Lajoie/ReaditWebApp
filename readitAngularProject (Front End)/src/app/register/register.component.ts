import { Component, OnInit } from '@angular/core';
import { AbstractControl, Form, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserDTO } from '../Models/UserDTO';
import { AuthService } from '../Service/auth.service';
import { FormBuilder } from '@angular/forms'
import { RegisterDTO } from '../Models/RegisterDTO';
import { UserService } from '../Service/user.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerUsername: string;
  registerPassword: string;
  registerPassword2: string;
  usernameField: FormControl;
  passwordField: FormControl;
  passwordField2: FormControl;
  hide = true;
  registerForm: FormGroup;
  passMatching: boolean;
  alreadyExist: boolean;
  userFound: UserDTO;
  registerDTO: RegisterDTO
  constructor(private router: Router, private authService: AuthService, private userService: UserService, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    if (sessionStorage.getItem(TOKEN_KEY)) {  // if already login, redirect to home page
      this.router.navigate(['Index']);
    }
    this.passMatching = false;
    this.alreadyExist = false;
    this.usernameField = new FormControl('', [Validators.required]);
    this.passwordField = new FormControl('', [Validators.required]);
    this.passwordField2 = new FormControl('', [Validators.required,]);
    this.registerForm = this.formBuilder.group({
      usernameField: ["", [Validators.required]],
      passwordField: ["", [Validators.required]],
      passwordField2: ["", [Validators.required]],
    });
  }

  createAccount() {
    if (this.passMatching) {                                         // make sure both passwords match
      this.registerDTO = new RegisterDTO();
      this.registerDTO.username = this.registerUsername;
      this.registerDTO.password = this.registerPassword;
      this.userService.createAccount(this.registerDTO).subscribe({
        next: (response) => {                                       // create successful
          console.log("Create account successful");
          this.router.navigate(['Login']);
        },
        error: (err) => {                                           // create failed
          console.log("Create account failed");
          //console.log(err);
        }
      });
    }
  }

  getErrorMessageUsername() {
    if (this.usernameField.hasError('required')) {
      return 'You must enter a username';
    }
    else if (this.usernameField.hasError('alreadyExist')) {
      return 'Username is already taken';
    }
  }

  getErrorMessagePassword() {
    if (this.passwordField.hasError('required')) {
      return 'You must enter a password';
    }
  }

  getErrorMessagePassword2() {
    if (this.passwordField2.hasError('required')) {
      return 'You must enter a password';
    }
    else if (this.passwordField2.hasError('notMatch')) {
      return "Error. Passwords do not match.";
    }
  }

  isMatching() {                                        //check if both passwords match
    if (this.registerPassword != this.registerPassword2 && !this.passwordField2.hasError('required')) {
      this.passMatching = false;
      this.passwordField2.setErrors({ 'notMatch': true });
    }
    else {
      this.passMatching = true;
      this.passwordField2.setErrors({ 'notMatch': false });
      this.passwordField2.updateValueAndValidity();
    }
  }


  isAlreadyExist() {
    if (this.registerUsername.length != 0) {
      this.userService.findByUsername(this.registerUsername)
        .subscribe((res) => {
          this.userFound = res.body as UserDTO;
          if (this.userFound != null) {
            this.usernameField.setErrors({ 'alreadyExist': true });
          }
          else {
            this.usernameField.setErrors({ 'alreadyExist': false });
            this.usernameField.updateValueAndValidity();
          }
        });
    }
  }
}


