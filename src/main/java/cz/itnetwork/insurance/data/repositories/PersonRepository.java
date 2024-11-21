package cz.itnetwork.insurance.data.repositories;

import cz.itnetwork.insurance.data.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
