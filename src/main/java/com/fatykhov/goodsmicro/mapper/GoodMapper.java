package com.fatykhov.goodsmicro.mapper;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface GoodMapper {
    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "type")
    @Mapping(target = "quantity")
    Good fromDto(GoodDto goodDto);

    @Mapping(target = "id")
    @Mapping(target = "name")
    @Mapping(target = "type")
    @Mapping(target = "quantity")
    GoodDto toDto(Good good);
}
