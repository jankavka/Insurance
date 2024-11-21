package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.data.repositories.InsuranceRepository;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsuranceServiceImpl implements InsuranceService{

    @Autowired
    InsuranceMapper insuranceMapper;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Override
    public void create(InsuranceDTO insuranceDTO) {
        InsuranceEnity insuranceEnity = insuranceMapper.toInsuranceEntity(insuranceDTO);
        insuranceRepository.save(insuranceEnity);
    }
}
