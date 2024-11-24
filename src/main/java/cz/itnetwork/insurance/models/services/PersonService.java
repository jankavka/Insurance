package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.models.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    void create(PersonDTO personDTO);

    List<PersonDTO> showAll();

    PersonDTO findById(long personId);

    PersonEntity fetchEntity(long personId);

    void saveUpdatedEntity(PersonDTO source);

    void delete(long personId);




}
