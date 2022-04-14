import { HttpClient } from '@angular/common/http';
import { ScriptService } from './../../../services/test/script.service';
import { Router } from '@angular/router';
import { ActiviteService } from './../../../services/activite.service';
import { LogementService } from './../../../services/logement.service';
import { Logement } from './../../../model/logement';
import { Component, OnInit, Renderer2, AfterViewInit } from '@angular/core';
import { Activite } from 'src/app/model/activite';
import * as L from 'leaflet';
import 'leaflet-routing-machine';
import Geocoder from 'leaflet-control-geocoder';
import { MapType } from '@angular/compiler';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { EtapeService } from 'src/app/services/etape.service';
import { ConnexionService } from 'src/app/services/connexion.service';

@Component({
  selector: 'app-activites-logements-list',
  templateUrl: './activites-logements-list.component.html',
  styleUrls: [
    './activites-logements-list.component.css',
    '../../../app.component.css',
  ],
})
export class ActivitesLogementsListComponent implements OnInit, AfterViewInit {
  form: FormGroup;
  activites: Activite[] = [];
  logements: Logement[] = [];
  ville: string = '';
  myfrugalmap: any;
  lat1: number = 50.6311634;
  lng1: number = 3.0599573;
  lat2: number = 47.22485;
  lng2: number = -1.60137;
  query: string = '';

  activitesReservees: number[] = [];
  logementsReserves: number = 0;

  isChecked: boolean = false;
  constructor(
    private http: HttpClient,
    private authService: ConnexionService,
    private activiteService: ActiviteService,
    private logementService: LogementService,
    private etapeService: EtapeService,
    private router: Router
  ) {
    this.form = new FormGroup({
      logementsArray: new FormArray([]),
      activitesArray: new FormArray([]),
    });
  }

  ngAfterViewInit(): void {}

  ngOnInit(): void {
    this.carte();
  }

  carte() {
    // Déclaration de la carte avec les coordonnées du centre et le niveau de zoom.
    this.myfrugalmap = L.map('frugalmap').setView([50.6311634, 3.0599573], 12);

    L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
      attribution: 'Frugal Map',
    }).addTo(this.myfrugalmap);

    L.Routing.control({
      router: L.Routing.osrmv1({
        serviceUrl: `http://router.project-osrm.org/route/v1/`,
        language: 'fr',
        profile: 'car', //car, bike
      }),
      geocoder: (L.Control as any).geocoder(),
      lineOptions: {
        styles: [
          {
            color: '#839c49',
            opacity: 1,
            weight: 7,
          },
        ],
        extendToWaypoints: true,
        missingRouteTolerance: 10,
      },
      waypoints: [L.latLng(48.856614, 2.3522219), L.latLng(43.604, 1.44305)],
    }).addTo(this.myfrugalmap);

    const myIcon = L.icon({
      iconUrl:
        'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.2.0/images/marker-icon.png',
    });
    L.marker([50.6311634, 3.0599573], { icon: myIcon })
      .bindPopup('Je suis un Frugal Marqueur')
      .addTo(this.myfrugalmap)
      .openPopup();
  }

  get logementsArray(): FormArray {
    return this.form.get('logementsArray') as FormArray;
  }
  get activitesArray(): FormArray {
    return this.form.get('activitesArray') as FormArray;
  }

  list() {
    this.activiteService.getAllByVille(this.ville).subscribe((result) => {
      this.activites = result;
      this.activites.forEach((activite) => {
        console.log(activite);
      });
      let formArray = this.form.get('activitesArray') as FormArray;
      this.activites.forEach((activite) => {
        formArray.push(new FormControl(false));
      });
    });
    this.logementService.getAllByVille(this.ville).subscribe((result) => {
      this.logements = result;
      this.logements.forEach((logement) => {
        console.log(logement);
      });
      //get adresse du logement
      //transformer en coord gps
      //ajouter un marqueur sur la carte
      let formArray = this.form.get('logementsArray') as FormArray;
      this.logements.forEach((logement) => {
        formArray.push(new FormControl(false));
      });
    });
  }

  /*addMarker() {
    L.Control.Geocoder.geocode(this.query, function (results) {
      L.marker([results[0].center.lat, results[0].center.lng])
        .bindPopup('Je suis un Frugal Marqueur')
        .addTo(this.myfrugalmap)
        .openPopup();
    });
  }*/

  /*geocoder.geocode({search:"Paris"}, a:any => {
  // depending on geocoder results may be either in a or b
  console.log(a);
  // choose the best result here. probably the first one in array
  // create waypoint object
  console.log(a[0].center.lat);
  var wpt = L.Routing.waypoint(
    L.latLng(a[0].center.lat, a[0].center.lng),
    name
  );
  console.log(wpt);
  waypoints.push(wpt);
  })/*


  /*addMarker(lat: string, lng: string, text: string) {
    let point = [lat, lng];
    this.myfrugalmap.bounds.push(point);
    return new LeafletMarker(point, text, this.myfrugalmap);
  }*/
  /*addressToCoordinates() {
    this.geocodeService
      .geocodeAddress(this.address)
      .subscribe((location: Location) => {
        this.location = location;
        console.log(this.location);
        this.loading = false;
        this.ref.detectChanges();
      });
  }*/

  etape() {
    let activites: any[] = [];
    this.activitesReservees.forEach((activite) => {
      let a = {
        id: activite,
      };
      activites.push(a);
    });
    let etape = {
      duree: '2',
      date: '2022-07-30',
      ville: this.ville,
      logement: {
        id: this.logementsReserves,
      },
      activites: activites,
    };
    console.log(activites);
    this.etapeService.create(etape).subscribe((ok) => {});
  }

  submit() {
    const selectedLogementsIds = this.form.value.logementsArray
      .map((checked: boolean, i: number) =>
        checked ? this.logements[i].id : null
      )
      .filter((v: any) => v !== null);

    this.logementsReserves = selectedLogementsIds[0];
    console.log(this.logementsReserves);
    const selectedActivitesIds = this.form.value.activitesArray
      .map((checked: boolean, i: number) =>
        checked ? this.activites[i].id : null
      )
      .filter((v: any) => v !== null);

    this.activitesReservees = selectedActivitesIds;
    console.log(this.activitesReservees);
    this.etape();
  }

  redirect() {
    this.router.navigateByUrl('/itineraire/etapes');
  }

  isAutenticated() {
    return this.authService.isAuthenticated();
  }
}
