import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RegisterRequest } from '../../../dto/register-request.model';
import { AuthService } from '../../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  user: RegisterRequest = {username: '', password: '', role: ''};
  errorMessage: string = '';

  constructor(private authService: AuthService) {}

  onRegister() {
    this.authService.register(this.user).subscribe(
      () => {
        alert('Inscription réussie !');
      },
      (error) => {
        this.errorMessage = error.error || 'Une erreur s\'est produite lors de l\'inscription.';
      }
    );
  }
}
