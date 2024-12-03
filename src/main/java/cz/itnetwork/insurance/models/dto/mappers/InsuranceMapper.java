package cz.itnetwork.insurance.models.dto.mappers;

import cz.itnetwork.insurance.data.entities.InsuranceEntity;
import cz.itnetwork.insurance.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceDTO toInsuranceDTO(InsuranceEntity insuranceEntity);

    InsuranceEntity toInsuranceEntity(InsuranceDTO insuranceDTO);

    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEntity target);

    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);


}
