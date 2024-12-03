package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEntity;
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
public class InsuranceServiceImpl implements InsuranceService {


    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonMapper personMapper;

    /**
     * Creates a new insurance record based on the provided InsuranceDTO.
     * <p>
     * This method maps the given InsuranceDTO to an InsuranceEntity using the insuranceMapper.
     * It retrieves the associated PersonEntity using the person ID from the DTO and sets it to the insurance entity.
     * Finally, it saves the insurance entity to the repository.
     *
     * @param insuranceDTO The data transfer object containing the details of the insurance policy to be created.
     *                     This object should include the necessary information, such as the person ID and other
     *                     relevant insurance details.
     */
    @Override
    public void create(InsuranceDTO insuranceDTO) {
        InsuranceEntity insuranceEntity = insuranceMapper.toInsuranceEntity(insuranceDTO);
        insuranceEntity.setPersonEntity(personService.fetchPersonEntity(insuranceDTO.getPersonId()));
        insuranceRepository.save(insuranceEntity);
    }

    /**
     * Retrieves all insurance records from the repository and converts them into a list of InsuranceDTOs.
     * <p>
     * This method fetches all insurance entities from the insurance repository, maps each entity to
     * its corresponding InsuranceDTO using the insuranceMapper, and populates the person information
     * for each insurance policy. It sets the associated person ID and person details in the DTOs.
     *
     * @return A list of InsuranceDTO objects, each representing an insurance policy along with the associated person details.
     * The list may be empty if no insurance records are found in the repository.
     */
    @Override
    public List<InsuranceDTO> showAllInsurances() {
        List<InsuranceDTO> list = new ArrayList<>();
        insuranceRepository.findAll().forEach(i -> list.add(insuranceMapper.toInsuranceDTO(i)));
        list.forEach(a -> a.setPersonId(insuranceRepository.findById(a.getId()).orElseThrow().getPersonEntity().getPersonId()));
        list.forEach(i -> i.setPersonDTO(personMapper.toPersonDTO(personRepository.findById(i.getPersonId()).orElseThrow())));

        return list;
    }

    /**
     * Retrieves an insurance policy by its unique identifier (ID) and converts it into an InsuranceDTO.
     * <p>
     * This method calls the fetchedInsuranceEntity method to retrieve the corresponding InsuranceEntity
     * and then maps it to an InsuranceDTO using the insuranceMapper.
     *
     * @param id The unique identifier of the insurance policy to be retrieved.
     * @return An InsuranceDTO object representing the insurance policy associated with the given ID,
     * or null if no insurance policy is found with the specified ID.
     */
    @Override
    public InsuranceDTO getById(long id) {
        return insuranceMapper.toInsuranceDTO(fetchedInsuranceEntity(id));
    }

    /**
     * Deletes the insurance policy associated with the provided unique identifier (ID).
     * <p>
     * This method calls the deleteById method from the insurance repository using the specified ID.
     * If no insurance policy is found with the given ID, the method will safely handle it
     * without throwing an exception.
     *
     * @param id The unique identifier of the insurance policy to be deleted.
     *           If no policy exists with the specified ID, no action will be taken.
     */
    @Override
    public void delete(long id) {
        insuranceRepository.deleteById(id);
    }


    /**
     * Retrieves a list of insurance policies associated with the specified person's unique identifier (person ID).
     * <p>
     * This method calls showInsuranceEntityList to fetch all insurance entities,
     * filters the list to include only those linked to the given person ID,
     * and then maps the filtered entities to InsuranceDTOs using the insuranceMapper.
     *
     * @param personId The unique identifier of the person whose insurance policies are to be retrieved.
     * @return A list of InsuranceDTO objects representing the insurance policies associated with the provided person ID.
     * This list may be empty if no insurance policies are found for the specified person.
     */
    @Override
    public List<InsuranceDTO> showInsuranceListByPersonId(long personId) {
        return showInsuranceEntityList().stream()
                .filter(i -> i.getPersonEntity().getPersonId() == personId)
                .map(i -> insuranceMapper.toInsuranceDTO(i))
                .toList();
    }

    /**
     * Updates an existing insurance policy based on the information provided in the given InsuranceDTO.
     * <p>
     * This method retrieves the insurance entity associated with the unique identifier from the DTO,
     * updates its details using the information from the DTO, and then saves the modified entity back to the repository.
     *
     * @param insuranceDTO The data transfer object containing the updated details of the insurance policy.
     *                     This object must include the unique identifier for the insurance policy to be updated,
     *                     along with any other relevant information necessary for the update process.
     * @throws InsuranceNotFoundException If no insurance entity is found with the specified ID in the DTO.
     */
    @Override
    public void updateInsurance(InsuranceDTO insuranceDTO) {
        InsuranceEntity fetchedEntity = insuranceRepository.findById(insuranceDTO.getId()).orElseThrow(InsuranceNotFoundException::new);
        insuranceMapper.updateInsuranceEntity(insuranceDTO, fetchedEntity);
        insuranceRepository.save(fetchedEntity);
    }

    /**
     * Retrieves the insurance entity associated with the provided unique identifier (ID).
     * <p>
     * This method calls the findById method of the insurance repository to find the corresponding
     * InsuranceEntity. If an entity with the specified ID does not exist, it throws an InsuranceNotFoundException.
     *
     * @param id The unique identifier of the insurance entity to be fetched.
     * @return An InsuranceEntity object representing the insurance record associated with the given ID.
     * @throws InsuranceNotFoundException If no insurance entity is found with the specified ID.
     */
    @Override
    public InsuranceEntity fetchedInsuranceEntity(long id) {
        return insuranceRepository.findById(id).orElseThrow(InsuranceNotFoundException::new);
    }

    /**
     * Retrieves a list of all insurance entities available in the repository.
     * <p>
     * This method uses the findAll method of the insurance repository to fetch all insurance records.
     * It converts the iterable returned by the repository into a List using StreamSupport for easier processing.
     *
     * @return A list of InsuranceEntity objects representing all insurance records in the repository.
     * This list may be empty if no insurance entities are present.
     */
    @Override
    public List<InsuranceEntity> showInsuranceEntityList() {
        return StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
                .toList();
    }


}
