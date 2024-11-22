package cz.itnetwork.insurance.models.dto.mappers;

import cz.itnetwork.insurance.data.entities.InsuranceEnity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceDTO toInsuranceDTO(InsuranceEnity insuranceEnity);

    InsuranceEnity toInsuranceEntity(InsuranceDTO insuranceDTO);

    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEnity target);

    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);

}
