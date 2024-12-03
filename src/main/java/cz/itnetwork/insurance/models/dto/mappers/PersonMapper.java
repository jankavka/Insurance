package cz.itnetwork.insurance.models.dto.mappers;

import cz.itnetwork.insurance.data.entities.PersonEntity;
import cz.itnetwork.insurance.models.dto.PersonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDTO toPersonDTO(PersonEntity personEntity);

    PersonEntity toPersonEntity(PersonDTO personDTO);

    void updatePersonDTO(PersonDTO source, @MappingTarget PersonDTO target);

    void updatePersonEntity(PersonDTO source, @MappingTarget PersonEntity target);


}
