package cz.itnetwork.insurance.models.services;


import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.data.repositories.PersonRepository;
import cz.itnetwork.insurance.models.dto.PersonDTO;
import cz.itnetwork.insurance.models.dto.mappers.PersonMapper;
import cz.itnetwork.insurance.models.exceptions.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonRepository personRepository;


    @Override
    public void create(PersonDTO personDTO) {
        PersonEntity personEntity = personMapper.toPersonEntity(personDTO);
        personRepository.save(personEntity);
    }

    @Override
    public List<PersonDTO> showAll() {

        return StreamSupport.stream(personRepository.findAll().spliterator(),false)
                .map(i -> (personMapper.toPersonDTO(i)))
                .toList();
    }

    @Override
    public PersonDTO findById(long personId) {
        PersonEntity personEntity = fetchEntity(personId);
        return personMapper.toPersonDTO(personEntity);
    }


    @Override
    public PersonEntity fetchEntity(long personId) {
        return personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public void saveUpdatedEntity(PersonDTO source) {
        PersonEntity fetchedEntity = fetchEntity(source.getPersonId());
        personMapper.updatePersonEntity(source,fetchedEntity);
        personRepository.save(fetchedEntity);
    }

    @Override
    public void delete(long personId) {
        personRepository.deleteById(personId);
    }
}
