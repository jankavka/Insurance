package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.PersonDTO;

import java.util.List;

public interface InsuranceService {

    void create(InsuranceDTO insuranceDTO);

    List<InsuranceDTO> insuranceList();

    InsuranceDTO getById(long id);

    void delete(long id);



}
