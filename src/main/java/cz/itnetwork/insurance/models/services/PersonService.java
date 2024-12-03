package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.models.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person record based on the provided data transfer object (DTO) containing person details.
     *
     * @param personDTO The data transfer object containing the information necessary for creating a new person record.
     *                  This object should include all required fields such as name, address, and contact information.
     */
    void create(PersonDTO personDTO);

    /**
     * Retrieves a list of all persons available in the system.
     *
     * @return A list of PersonDTO objects, each representing an individual person with their associated details.
     * This list may be empty if no persons are found in the system.
     */
    List<PersonDTO> showAllPersons();

    /**
     * Retrieves a person record based on the provided unique identifier (person ID).
     *
     * @param personId The unique identifier of the person to be retrieved.
     * @return A PersonDTO object representing the person associated with the given person ID,
     * or null if no person is found with the specified ID.
     */
    PersonDTO findById(long personId);

    /**
     * Retrieves the person entity associated with the provided unique identifier (person ID).
     *
     * @param personId The unique identifier of the person entity to be fetched.
     * @return A PersonEntity object representing the person record associated with the given person ID,
     * or null if no person entity is found with the specified ID.
     */
    PersonEntity fetchPersonEntity(long personId);

    /**
     * Updates an existing person record based on the information provided in the given data transfer object (DTO).
     *
     * @param source The data transfer object containing the updated details of the person.
     *               This object must include the unique identifier for the person to be updated,
     *               along with any other relevant information necessary for the update process.
     */
    void updatePerson(PersonDTO source);

    /**
     * Deletes the person record identified by the provided unique identifier (person ID).
     *
     * @param personId The unique identifier of the person to be deleted.
     *                 If no person record is found with the specified ID, no action will be taken.
     */
    void delete(long personId);


}
