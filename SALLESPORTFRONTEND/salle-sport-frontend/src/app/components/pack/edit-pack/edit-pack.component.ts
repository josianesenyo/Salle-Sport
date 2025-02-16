import { Component, Input, OnInit } from '@angular/core';
import { Pack } from '../../../dto/pack.model';
import { PackService } from '../../../services/pack.service';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-pack',
  imports: [FormsModule],
  templateUrl: './edit-pack.component.html',
  styleUrl: './edit-pack.component.css'
})
export class EditPackComponent implements OnInit{
  @Input()
  pack!: Pack;

  constructor(
    private packService: PackService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const packId = this.route.snapshot.paramMap.get('id');
    if (packId) {
      this.packService.getPackById(+packId).subscribe(
        (data) => {
          this.pack = data;
          console.log('Offre récupérée :', this.pack);
        },
        (error) => {
          console.error('Erreur lors de la récupération de l\'offre :', error);
        }
      );
    } else {
      console.error('ID de l\'offre non trouvé dans les paramètres de la route.');
    }
  }

  onUpdatePack() {
    this.packService.updatePack(this.pack.id, this.pack).subscribe(
      () => {
        alert('Offre mise à jour avec succès !');
        this.router.navigate(['/packs']);
        // Redirection vers la liste des offres ou fermeture du formulaire
      },
      (error) => {
        console.error('Erreur lors de la mise à jour de l\'offre :', error);
      }
    );
  }

}
