import { routes } from './routes';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { HomeComponent } from './component/home/home.component';
import { ConnexionComponent } from './component/connexion/connexion.component';
import { InscriptionComponent } from './component/inscription/inscription.component';
import { CompteModifComponent } from './component/compte-modif/compte-modif.component';
import { HoteInscriptionComponent } from './component/inscription/hote-inscription/hote-inscription.component';
import { OrganisateurInscriptionComponent } from './component/inscription/organisateur-inscription/organisateur-inscription.component';
import { ClientInscriptionComponent } from './component/inscription/client-inscription/client-inscription.component';

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    HomeComponent,
    InscriptionComponent,
    ConnexionComponent,
    CompteModifComponent,
    HoteInscriptionComponent,
    OrganisateurInscriptionComponent,
    ClientInscriptionComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
