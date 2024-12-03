package cz.itnetwork.insurance.data.repositories;

import cz.itnetwork.insurance.data.entities.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {
}
