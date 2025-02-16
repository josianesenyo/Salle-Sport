import { Component, Input, OnInit } from '@angular/core';
import { Pack } from '../../../dto/pack.model';
import { PackService } from '../../../services/pack.service';
import { ActivatedRoute, Route, Router } from '@angular/router';

@Component({
  selector: 'app-view-pack',
  imports: [],
templateUrl: './view-pack.component.html',
  styleUrl: './view-pack.component.css'
})
export class ViewPackComponent implements OnInit{
  
  pack!: Pack ;
  packs: Pack[] = [];

  constructor(
    private packService: PackService,
    private route: ActivatedRoute,
    private router: Router) {}

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

  goBack() {
    this.router.navigate(['/pack-list']);
  }
  
  // ngOnInit() {
  //   // Assurez-vous que le pack est défini avant de l'afficher
  //   if (!this.pack) {
  //     console.error('Aucune offre n\'a été passée au composant.');
  //   }
  // }
}
