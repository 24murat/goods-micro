package com.fatykhov.goodsmicro.mapper;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface GoodMapper {
    Good fromDto(GoodDto goodDto);

    GoodDto toDto(Good good);

    List<GoodDto> toDtoList(List<Good> goods);
}
