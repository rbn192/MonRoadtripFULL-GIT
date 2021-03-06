import { Logement } from './../model/logement';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LogementService {
  private static URL: string = 'http://localhost:8080/monroadtrip/api/logement';

  constructor(private http: HttpClient) {}

  public getAllByHote(mail: string): Observable<any[]> {
    return this.http.get<any[]>(`${LogementService.URL}/hote/${mail}`);
  }

  public getAllByVille(ville: string): Observable<Logement[]> {
    return this.http.get<Logement[]>(
      `${LogementService.URL}/logement/${ville}`
    );
  }

  public get(id: number): Observable<any> {
    return this.http.get<any>(`${LogementService.URL}/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<any>(`${LogementService.URL}/${id}`);
  }

  public create(logement: Logement): Observable<any> {
    return this.http.post(LogementService.URL, this.logementToJson(logement));
  }

  public update(logement: Logement): Observable<any> {
    console.log(this.logementToJson(logement));
    return this.http.put(
      `${LogementService.URL}/${logement.id}`,
      this.logementToJson(logement)
    );
  }

  private logementToJson(logement: Logement): any {
    let obj = {
      id: logement.id,
      date: logement.date,
      prix: logement.prix,
      adresse: {
        cp: logement.adresse?.cp,
        voie: logement.adresse?.voie,
        numero: logement.adresse?.numero,
        ville: logement.adresse?.ville,
      },
      nbPlaces: logement.nbPlaces,
      hote: {
        type: 'hote',
        id: logement.hote?.id,
        prenom: logement.hote?.prenom,
        mail: logement.hote?.mail,
        dateNaissance: logement.hote?.dateNaissance,
      },
    };
    return obj;
  }
}
