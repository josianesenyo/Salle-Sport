import { Component } from '@angular/core';
import { AuthService } from '../../../services/auth.service';
import { LoginResponse } from '../../../dto/login-request.model';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router){}

  onLogin() {
    this.authService.login(this.username, this.password).subscribe({
      next: (response: LoginResponse) => {
        localStorage.setItem('token', response.token);
        alert('Connexion réussie !');
        this.router.navigate(['/customers']);
      },
      error: (error) => {
        console.error('Erreur lors de la connexion :', error);
        if (error.error && typeof error.error === 'string') {
          this.errorMessage = error.error;
        } else {
          this.errorMessage = 'Identifiant ou mot de passe incorrect ❌❌❌';
        }
        this.password = '';
      }
    }
  );
}

}
