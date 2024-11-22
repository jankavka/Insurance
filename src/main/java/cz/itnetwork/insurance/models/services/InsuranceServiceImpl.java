package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.data.repositories.InsuranceRepository;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<InsuranceDTO> insuranceList() {
        List<InsuranceDTO> list = new ArrayList<>();
        insuranceRepository.findAll().forEach(i -> list.add(insuranceMapper.toInsuranceDTO(i)));
        return list;
    }

    @Override
    public void edit(InsuranceDTO insuranceDTO) {
        InsuranceEnity oldEntity = insuranceRepository.findById(insuranceDTO.getId()).get();
        insuranceMapper.updateInsuranceEntity(insuranceDTO,oldEntity);
        insuranceRepository.save(oldEntity);
    }

    @Override
    public InsuranceDTO getById(long id) {
        return insuranceMapper.toInsuranceDTO(insuranceRepository.findById(id).get());
    }

    @Override
    public void deleteInsurance(long id) {
        insuranceRepository.deleteById(id);
    }


}
