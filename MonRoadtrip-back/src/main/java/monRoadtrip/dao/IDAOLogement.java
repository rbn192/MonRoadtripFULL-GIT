package monRoadtrip.dao;

import java.util.List;

import monRoadtrip.model.Logement;

public interface IDAOLogement extends IDAO<Logement,Integer>{

	public List<Logement> findAllDisponibles();	
}
