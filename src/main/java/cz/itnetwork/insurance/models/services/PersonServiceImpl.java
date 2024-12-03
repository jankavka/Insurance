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
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Creates a new person record based on the information provided in the given PersonDTO.
     * <p>
     * This method converts the provided PersonDTO into a PersonEntity using the personMapper
     * and then saves the new person entity to the repository.
     *
     * @param personDTO The data transfer object containing the details of the person to be created.
     *                  This object should include all necessary fields required to create a new person record,
     *                  such as name, address, and contact information.
     */
    @Override
    public void create(PersonDTO personDTO) {
        PersonEntity personEntity = personMapper.toPersonEntity(personDTO);
        personRepository.save(personEntity);
    }

    /**
     * Retrieves a list of all persons available in the repository and converts them into PersonDTOs.
     * <p>
     * This method uses the findAll method of the person repository to fetch all person entities,
     * then maps each entity to its corresponding PersonDTO using the personMapper,
     * and finally collects the results into a list.
     *
     * @return A list of PersonDTO objects, each representing an individual person with their associated details.
     * This list may be empty if no persons are found in the repository.
     */
    @Override
    public List<PersonDTO> showAllPersons() {

        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(i -> (personMapper.toPersonDTO(i)))
                .toList();
    }

    /**
     * Retrieves a person record by its unique identifier (person ID) and converts it into a PersonDTO.
     * <p>
     * This method first fetches the corresponding PersonEntity by calling the fetchPersonEntity method
     * with the specified person ID, and then maps the retrieved entity to a PersonDTO using the personMapper.
     *
     * @param personId The unique identifier of the person to be retrieved.
     * @return A PersonDTO object representing the person associated with the given person ID,
     * or null if no person is found with the specified ID.
     */
    @Override
    public PersonDTO findById(long personId) {
        PersonEntity personEntity = fetchPersonEntity(personId);
        return personMapper.toPersonDTO(personEntity);
    }

    /**
     * Retrieves the person entity associated with the provided unique identifier (person ID).
     * <p>
     * This method calls the findById method of the person repository to locate the corresponding
     * PersonEntity. If no entity with the specified person ID is found, it throws a PersonNotFoundException.
     *
     * @param personId The unique identifier of the person entity to be fetched.
     * @return A PersonEntity object representing the person associated with the given person ID.
     * @throws PersonNotFoundException If no person entity is found with the specified person ID.
     */
    @Override
    public PersonEntity fetchPersonEntity(long personId) {
        return personRepository.findById(personId).orElseThrow(PersonNotFoundException::new);
    }

    /**
     * Updates an existing person record based on the information provided in the given PersonDTO.
     * <p>
     * This method retrieves the person entity associated with the ID from the source DTO,
     * updates the entity's details using the information from the DTO,
     * and then saves the modified entity back to the repository.
     *
     * @param source The data transfer object containing the updated details of the person.
     *               This object must include the unique identifier for the person to be updated,
     *               along with any other relevant information necessary for the update process.
     * @throws PersonNotFoundException If no person entity is found with the specified ID in the source DTO.
     */
    @Override
    public void updatePerson(PersonDTO source) {
        PersonEntity fetchedEntity = fetchPersonEntity(source.getPersonId());
        personMapper.updatePersonEntity(source, fetchedEntity);
        personRepository.save(fetchedEntity);
    }

    /**
     * Deletes the person record associated with the provided unique identifier (person ID).
     * <p>
     * This method calls the deleteById method from the person repository using the specified person ID.
     * If no person record is found with the given ID, the method will handle it gracefully without throwing an exception.
     *
     * @param personId The unique identifier of the person to be deleted.
     *                 If no person exists with the specified ID, no action will be taken.
     */
    @Override
    public void delete(long personId) {
        personRepository.deleteById(personId);
    }

}
