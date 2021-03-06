package soprajc.monRoadtrip.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;



@Entity
public class Logement {

	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@JsonView(JsonViews.Common.class)
	private LocalDate date;	
	@JsonView(JsonViews.Common.class)
	private double prix;
	@Embedded
	@JsonView(JsonViews.Common.class)
	private Adresse adresse;
	@JsonView(JsonViews.Common.class)
	@Column(name = "nb_places")
	private int nbPlaces;
	@JsonView(JsonViews.Common.class)
	private int note; //1-10
	
	@ManyToOne
	@JoinColumn(name="id_hote_fk")
	@JsonView(JsonViews.Common.class)
	private Hote hote;
	
	@OneToMany (mappedBy = "logement")
	private List<Etape> etapes;
	
	@Version
	private int version;

	public Logement() {
	}    

	public Logement(Integer id, LocalDate date, double prix, Adresse adresse, int nbPlaces, int note, Hote hote) {
		this.date = date;
		this.prix = prix;
		this.adresse = adresse;
		this.nbPlaces = nbPlaces;
		this.note = note;
		this.hote = hote;
		this.id = id;
	}
	
	public Logement(Integer id, LocalDate date, double prix, Adresse adresse, int nbPlaces, Hote hote) {
		this.date = date;
		this.prix = prix;
		this.adresse = adresse;
		this.nbPlaces = nbPlaces;
		this.hote = hote;
		this.id = id;
	}

	public Logement(LocalDate date, double prix, Adresse adresse, int nbPlaces, int note, Hote hote) {
		this.date = date;
		this.prix = prix;
		this.adresse = adresse;
		this.nbPlaces = nbPlaces;
		this.note = note;
		this.hote = hote;
	}
	
	public Logement(LocalDate date, double prix, Adresse adresse, int nbPlaces, Hote hote) {
		this.date = date;
		this.prix = prix;
		this.adresse = adresse;
		this.nbPlaces = nbPlaces;
		this.hote = hote;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getPrix() {
		return prix;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public int getNote() {
		return note;
	}

	public Hote getHote() {
		return hote;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public void setHote(Hote hote) {
		this.hote = hote;
	}


	public List<Etape> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<Etape> etapes) {
		this.etapes = etapes;
	}
	

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}


	@Override
	public String toString() {
		return "Logement [id=" + id + ", date=" + date + ", prix=" + prix + ", adresse=" + adresse + ", nbPlaces="
				+ nbPlaces + ", note=" + note + ", hote=" + hote + "]";
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
		Logement other = (Logement) obj;
		return Objects.equals(id, other.id);
	}





}
