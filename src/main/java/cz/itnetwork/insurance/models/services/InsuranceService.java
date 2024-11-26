package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;


import java.util.List;

public interface InsuranceService {

    void create(InsuranceDTO insuranceDTO);

    List<InsuranceDTO> insuranceList();

    List<InsuranceEnity> insuranceEntityList();

    InsuranceDTO getById(long id);

    void delete(long id);

    List<InsuranceDTO> insuranceListById(long personId);

    void saveUpdatedInsurance(InsuranceDTO insuranceDTO);

    InsuranceEnity fetchedEntity(long id);





}
