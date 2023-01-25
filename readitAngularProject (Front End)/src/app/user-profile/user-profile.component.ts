import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterDTO } from '../Models/RegisterDTO';
import { UserDTO } from '../Models/UserDTO';
import { AuthService } from '../Service/auth.service';
import { UserService } from '../Service/user.service';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  registerDTO: RegisterDTO;
  usernameInput: string;
  passwordInput1: string;
  passwordInput2: string;
  passMatching: boolean;
  alreadyExist: boolean;
  usernameField: FormControl;
  passwordField: FormControl;
  passwordField2: FormControl;
  editAccountForm: FormGroup;
  userFound: UserDTO;
  hide = true;
  receivedUserDTO: RegisterDTO;
  userReceived: UserDTO;
  
  constructor(private router: Router, public userService: UserService,private authService: AuthService, private formBuilder: FormBuilder) { }
  
  ngOnInit(): void {
    this.passMatching = false;
    this.alreadyExist = false;
    this.usernameField = new FormControl('', [Validators.required]);
    this.passwordField = new FormControl('', [Validators.required]);
    this.passwordField2 = new FormControl('', [Validators.required,]);
    this.editAccountForm = this.formBuilder.group({
      usernameField: ["", [Validators.required]],
      passwordField: ["", [Validators.required]],
      passwordField2: ["", [Validators.required]],
    });
    this.usernameInput = this.userService.getCurrentUser().username;
  }

  editAccount() {
    this.registerDTO = new RegisterDTO();
    this.registerDTO.username = this.usernameInput;
    this.registerDTO.password = this.passwordInput1;
    this.userService.updateAccount(this.registerDTO, this.userService.getCurrentUser().id).subscribe({
      next: (response) => {                                       // create successful
        this.userService.setCurrentUser(this.receivedUserDTO);
        this.authService.authenticate(this.registerDTO.username, this.registerDTO.password).subscribe({
          next: (response) => {                                      
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
            this.router.navigate(['Index']);
          },
          error: (err) => {                                            
            console.log("Profile Edit Failed");
            //console.log(err);
          }
        });
      },
      error: (err) => {                                           // create failed
        console.log("Create post failed");
        //console.log(err);
      }
    });
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
    if (this.passwordInput1 != this.passwordInput1 && !this.passwordField2.hasError('required')) {
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
    if (this.usernameInput.length != 0) {
      this.userService.findByUsername(this.usernameInput)
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
