import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { PackService } from '../../../services/pack.service';
import { Pack } from '../../../dto/pack.model';
import { CommonModule } from '@angular/common';
import { PackDto } from '../../../dto/packDto.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-pack',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-pack.component.html',
  styleUrl: './add-pack.component.css'
})
export class AddPackComponent {

  newPack: PackDto = {
    offerName: '',
    durationMonths: 0,
    monthlyPrice: 0
  };

  constructor(
    private packService: PackService,
    private router: Router
  ) {}


  onAddPack() {
    this.packService.createPackDto(this.newPack).subscribe(
      () => {
      alert('Offre ajoutée avec succès');
      this.newPack = {
        offerName: '',
        durationMonths: 0,
        monthlyPrice: 0
      };
      this.router.navigate(['/packs']);

    }, (error) => {
      console.error('Erreur lors de l\'ajout de l\'offre', error);
    })
  }

  
}
