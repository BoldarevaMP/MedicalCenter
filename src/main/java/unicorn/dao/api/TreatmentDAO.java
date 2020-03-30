package unicorn.dao.api;

import unicorn.entity.Treatment;

import java.util.List;

public interface TreatmentDAO extends GenericDAO<Treatment> {

    Treatment getByName(String name);

    List<Treatment> getByLikeName(String name);
}
