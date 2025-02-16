import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pack } from '../dto/pack.model';
import { Observable } from 'rxjs';
import { PackDto } from '../dto/packDto.model';

@Injectable({
  providedIn: 'root'
})
export class PackService {
  private apiUrl = 'http://localhost:8080/api/packs';

  constructor(private http: HttpClient) {}

  createPack(pack: Pack): Observable<Pack> {
    return this.http.post<Pack>(this.apiUrl, pack);
  }

  createPackDto(packDto: PackDto): Observable<PackDto> {
    return this.http.post<PackDto>(this.apiUrl, packDto);
  }

  getPacks(): Observable<Pack[]> {
    return this.http.get<Pack[]>(this.apiUrl);
  }

  getPackById(id: number): Observable<Pack> {
    return this.http.get<Pack>(`${this.apiUrl}/${id}`);
  }

  updatePack(id: number, pack: Pack): Observable<Pack> {
    return this.http.put<Pack>(`${this.apiUrl}/${id}`, pack);
  }

  deletePack(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchPacksByOfferName(offerName: string): Observable<Pack[]> {
    return this.http.get<Pack[]>(`${this.apiUrl}/search?offerName=${offerName}`);
  }
}
