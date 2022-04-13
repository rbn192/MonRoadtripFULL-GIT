import { ReservationService } from 'src/app/services/reservation.service';
import { Participant } from 'src/app/model/participant';
import { Roadtrip } from 'src/app/model/roadtrip';
import { Reservation } from 'src/app/model/reservation';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reservations-list',
  templateUrl: './reservations-list.component.html',
  styleUrls: ['./reservations-list.component.css'],
})
export class ReservationsListComponent implements OnInit {
  reservations: Reservation[] = [];
  reservation: Reservation = new Reservation();
  roadtrip: Roadtrip = new Roadtrip();
  participant: Participant = new Participant();

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.list(localStorage.getItem('login')!);
  }

  list(mail: string) {
    this.reservationService.getAllByClient(mail).subscribe((result) => {
      this.reservations = result;
    });
  }

  delete(id: number) {
    this.reservationService.delete(id).subscribe((ok) => {
      this.list(localStorage.getItem('login')!);
    });
  }
}
