package soprajc.monRoadtrip.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Roadtrip {
	
	@JsonView(JsonViews.Common.class)
	@Column(name="depart_lieu")
	@NotEmpty
	private String departLieu;
	
	@JsonView(JsonViews.Common.class)
	@NotEmpty
	private String destination;
	
	@JsonView(JsonViews.Common.class)
	@Column(name="date_depart")
	@Future
	private LocalDate dateDepart;
	
	@JsonView(JsonViews.Common.class)
	@Column(name="date_arrivee")
	@Future
	private LocalDate dateArrivee;
	
	@JsonView(JsonViews.Common.class)
	private Double prix;
	
	@OneToMany(mappedBy = "roadtrip")
	private List<Reservation> reservations;
	
	@JsonView(JsonViews.Common.class)
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "ENUM('Voiture','Moto','Bus','Train','Avion')")
	private Transport transport;
	
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Version
	private int version;

	
	public Roadtrip() {}
	
	
	public Roadtrip(Integer id, String departLieu, String destination, LocalDate dateDepart, LocalDate dateArrivee, Double prix, Transport transport) {
		this.departLieu = departLieu;
		this.destination = destination;
		this.dateDepart = dateDepart;
		this.dateArrivee = dateArrivee;
		this.prix = prix;
		this.transport = transport;
		this.id=id;
	}
	
	public Roadtrip(String departLieu, String destination, LocalDate dateDepart, LocalDate dateArrivee, Double prix, Transport transport) {
		this.departLieu = departLieu;
		this.destination = destination;
		this.dateDepart = dateDepart;
		this.dateArrivee = dateArrivee;
		this.prix = prix;
		this.transport = transport;
	}

	public String getDepartLieu() {
		return departLieu;
	}

	public String getDestination() {
		return destination;
	}

	public LocalDate getDateDepart() {
		return dateDepart;
	}

	public LocalDate getDateArrivee() {
		return dateArrivee;
	}

	public Double getPrix() {
		return prix;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setDepartLieu(String departLieu) {
		this.departLieu = departLieu;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDateDepart(LocalDate dateDepart) {
		this.dateDepart = dateDepart;
	}

	public void setDateArrivee(LocalDate dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public int getVersion() {
		return version;
	}


	public void setVersion(int version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "Roadtrip [id=" + id +", departLieu=" + departLieu + ", destination=" + destination + ", dateDepart=" + dateDepart
				+ ", dateArrivee=" + dateArrivee + ", prix=" + prix + ", reservations=" + reservations + ", transport="
				+ transport + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Roadtrip other = (Roadtrip) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
