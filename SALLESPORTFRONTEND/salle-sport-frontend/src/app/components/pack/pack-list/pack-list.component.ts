import { Component } from '@angular/core';
import { Pack } from '../../../dto/pack.model';
import { HttpClient } from '@angular/common/http';
import { PackService } from '../../../services/pack.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-pack-list',
  imports: [FormsModule, CommonModule],
  templateUrl: './pack-list.component.html',
  styleUrl: './pack-list.component.css'
})
export class PackListComponent {
  searchTerm: string = '';
  foundPacks: Pack[] = [];
  packs: Pack[] = [];
  errorMessage: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private packService: PackService,
    private authService: AuthService) {}

  ngOnInit() {
    this.packService.getPacks().subscribe(data => {
      this.packs = data;
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  onSearch() {
    if (this.searchTerm.trim() !== '') {
      this.packService.searchPacksByOfferName(this.searchTerm).subscribe(
        (data) => {
          this.foundPacks = data;
        },
        (error) => {
          console.error('Erreur lors de la recherche des offres :', error);
          if(error.status === 500) {
            this.errorMessage = "Impossible de supprimer ce offre. Des clients sont souscrient à cet abonnement.";
          } else {this.errorMessage = 'Erreur la suppression de l\'offre.';}
        }
      );
    }
  }


  onEdit(pack: Pack) {
    this.router.navigate(['/edit-pack', pack.id]);
  }

  onDelete(id: any) {
    // Implémentez la logique pour supprimer un pack
    if (confirm('Êtes-vous sûr de vouloir supprimer cette offre ?')) {
      this.packService.deletePack(id).subscribe(
        () => {
          alert('Offre supprimée avec succès !');
          this.packs = this.packs.filter(p => p.id !== id);
        },
        (error) => {
          console.error('Erreur lors de la suppression de l\'offre :', error);
        }
      );
    } else {
      this.errorMessage = '';
    }
  }

  onView(pack: Pack) {
    this.router.navigate(['/view-pack', pack.id]);
  }
}
