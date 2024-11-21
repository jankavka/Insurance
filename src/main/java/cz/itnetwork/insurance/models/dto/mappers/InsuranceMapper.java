package cz.itnetwork.insurance.models.dto.mappers;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceDTO toInsuranceDTO(InsuranceEnity insuranceEnity);

    InsuranceEnity toInsuranceEntity(InsuranceDTO insuranceDTO);

}
