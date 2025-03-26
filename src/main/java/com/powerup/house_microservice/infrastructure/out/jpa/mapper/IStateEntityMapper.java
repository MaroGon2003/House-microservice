package com.powerup.house_microservice.infrastructure.out.jpa.mapper;

import com.powerup.house_microservice.domain.model.StateModel;
import com.powerup.house_microservice.infrastructure.out.jpa.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;



@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IStateEntityMapper {

    StateEntity toStateEntity(StateModel stateModel);

    StateModel toStateModel(StateEntity stateEntity);

}
