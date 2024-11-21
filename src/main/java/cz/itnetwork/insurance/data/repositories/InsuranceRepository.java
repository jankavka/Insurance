package cz.itnetwork.insurance.data.repositories;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<InsuranceEnity, Long> {
}
