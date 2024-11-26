package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.data.repositories.InsuranceRepository;
import cz.itnetwork.insurance.data.repositories.PersonRepository;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import cz.itnetwork.insurance.models.dto.mappers.InsuranceMapper;
import cz.itnetwork.insurance.models.dto.mappers.PersonMapper;
import cz.itnetwork.insurance.models.exceptions.InsuranceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuranceServiceImpl implements InsuranceService{
    @Override
    public List<InsuranceEnity> insuranceEntityList() {
        return StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
                .toList();
    }

    @Autowired
    InsuranceMapper insuranceMapper;

    @Autowired
    InsuranceRepository insuranceRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonService personService;

    @Autowired
    PersonMapper personMapper;

    @Override
    public void create(InsuranceDTO insuranceDTO) {
        InsuranceEnity insuranceEnity = insuranceMapper.toInsuranceEntity(insuranceDTO);
        insuranceEnity.setPersonEntity(personService.fetchEntity(insuranceDTO.getPersonId()));
        insuranceRepository.save(insuranceEnity);
    }

    @Override
    public List<InsuranceDTO> insuranceList() {
        List<InsuranceDTO> list = new ArrayList<>();
        insuranceRepository.findAll().forEach(i -> list.add(insuranceMapper.toInsuranceDTO(i)));
        list.forEach(a-> a.setPersonId(insuranceRepository.findById(a.getId()).orElseThrow().getPersonEntity().getPersonId()));
        list.forEach(i-> i.setPersonDTO(personMapper.toPersonDTO(personRepository.findById(i.getPersonId()).orElseThrow())));
        return list;

    }

    @Override
    public InsuranceDTO getById(long id) {
        return insuranceMapper.toInsuranceDTO(fetchedEntity(id));
    }

    @Override
    public void delete(long id) {
        insuranceRepository.deleteById(id);
    }

    @Override
    public List<InsuranceDTO> insuranceListById(long personId) {
        return insuranceEntityList().stream()
                .filter(i -> i.getPersonEntity().getPersonId() == personId)
                .map(i -> insuranceMapper.toInsuranceDTO(i))
                .toList();
    }


    @Override
    public void saveUpdatedInsurance(InsuranceDTO insuranceDTO){
        InsuranceEnity fetchedEntity = insuranceRepository.findById(insuranceDTO.getId()).orElseThrow(InsuranceNotFoundException::new);
        insuranceMapper.updateInsuranceEntity(insuranceDTO, fetchedEntity);
        insuranceRepository.save(fetchedEntity);

    }

    @Override
    public InsuranceEnity fetchedEntity(long id) {
        return insuranceRepository.findById(id).orElseThrow(InsuranceNotFoundException::new);
    }


}
