package cz.itnetwork.insurance.models.services;

import cz.itnetwork.insurance.data.entities.InsuranceEntity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;


import java.util.List;

public interface InsuranceService {

    /**
     * Creates a new insurance policy based on the provided insurance data transfer object (DTO).
     *
     * @param insuranceDTO The data transfer object containing the details of the insurance policy to be created.
     *                     The object should include all necessary information required for the creation process.
     */
    void create(InsuranceDTO insuranceDTO);

    /**
     * Retrieves a list of all insurance policies available in the system.
     *
     * @return A list of InsuranceDTO objects, each representing an insurance policy with its associated details.
     * This list may be empty if no insurance policies are found.
     */
    List<InsuranceDTO> showAllInsurances();

    /**
     * Retrieves a list of all insurance entities available in the database.
     *
     * @return A list of InsuranceEntity objects, each representing an insurance record within the system.
     * This list may be empty if no insurance entities are found.
     */
    List<InsuranceEntity> showInsuranceEntityList();

    /**
     * Retrieves an insurance policy based on the provided unique identifier (ID).
     *
     * @param id The unique identifier of the insurance policy to be retrieved.
     * @return An InsuranceDTO object representing the insurance policy associated with the given ID,
     * or null if no insurance policy is found with the specified ID.
     */
    InsuranceDTO getById(long id);

    /**
     * Deletes an insurance policy identified by the provided unique identifier (ID).
     *
     * @param id The unique identifier of the insurance policy to be deleted.
     *           If no insurance policy with the specified ID is found, no action will be taken.
     */
    void delete(long id);

    /**
     * Retrieves a list of insurance policies associated with the specified person's unique identifier (person ID).
     * <p>
     * This method filters the insurance records to return only those that are linked to the provided person ID.
     *
     * @param personId The unique identifier of the person whose insurance policies are to be retrieved.
     * @return A list of InsuranceDTO objects representing the insurance policies associated with the given person ID.
     * This list may be empty if no insurance policies are found for the specified person.
     */
    List<InsuranceDTO> showInsuranceListByPersonId(long personId);

    /**
     * Updates an existing insurance policy based on the provided insurance data transfer object (DTO).
     *
     * @param insuranceDTO The data transfer object containing the updated details of the insurance policy.
     *                     This object must include the unique identifier for the insurance policy to be updated,
     *                     along with any other relevant information necessary for the update process.
     */
    void updateInsurance(InsuranceDTO insuranceDTO);

    /**
     * Retrieves the insurance entity associated with the provided unique identifier (ID).
     *
     * @param id The unique identifier of the insurance entity to be fetched.
     * @return An InsuranceEntity object representing the insurance record associated with the given ID,
     * or null if no insurance entity is found with the specified ID.
     */
    InsuranceEntity fetchedInsuranceEntity(long id);


}
