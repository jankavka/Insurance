package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.models.dto.InsuranceDTO;

import java.util.List;

public interface InsuranceService {

    void create(InsuranceDTO insuranceDTO);

    List<InsuranceDTO> insuranceList();

    void edit(InsuranceDTO insuranceDTO);

    InsuranceDTO getById(long id);

    void deleteInsurance(long id);
}
