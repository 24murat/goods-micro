package com.fatykhov.goodsmicro.mapper;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import com.fatykhov.goodsmicro.entity.GoodType;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoodMapperTest {

    private final GoodMapper goodMapper = Mappers.getMapper(GoodMapper.class);

    @Test
    public void testFromDto() {
        GoodDto goodDto = new GoodDto(1L, "name", GoodType.LAPTOP, 10);
        Good good = goodMapper.fromDto(goodDto);
        assertEquals(goodDto.id(), good.getId());
        assertEquals(goodDto.name(), good.getName());
        assertEquals(goodDto.type(), good.getType());
        assertEquals(goodDto.quantity(), good.getQuantity());
    }

    @Test
    public void testToDto() {
        Good good = new Good(1L, "name", GoodType.LAPTOP, 10);
        GoodDto goodDto = goodMapper.toDto(good);
        assertEquals(good.getId(), goodDto.id());
        assertEquals(good.getName(), goodDto.name());
        assertEquals(good.getType(), goodDto.type());
        assertEquals(good.getQuantity(), goodDto.quantity());
    }
}

