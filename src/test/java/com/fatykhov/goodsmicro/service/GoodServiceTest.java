package com.fatykhov.goodsmicro.service;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import com.fatykhov.goodsmicro.entity.GoodType;
import com.fatykhov.goodsmicro.mapper.GoodMapper;
import com.fatykhov.goodsmicro.repository.GoodRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
public class GoodServiceTest {

    @Mock
    private GoodRepository goodRepository;

    @Mock
    private GoodMapper goodMapper;

    @InjectMocks
    private GoodService goodService;

    @Test
    public void testFindAll() {
        List<Good> list = List.of(new Good());
        List<GoodDto> dtoList = list.stream()
                .map(goodMapper::toDto)
                .toList();
        when(goodRepository.findAll()).thenReturn(list);
        when(goodMapper.toDtoList(list)).thenReturn(dtoList);
        assertEquals(1, goodService.findAll().size());
    }

    @Test
    public void testFindOne() {
        Good good = new Good();
        good.setId(1L);
        when(goodRepository.findById(1L)).thenReturn(Optional.of(good));
        when(goodMapper.toDto(good)).thenReturn(new GoodDto(1L, "name", GoodType.LAPTOP, 10));
        assertNotNull(goodService.findById(1L));
    }

    @Test
    public void testSave() {
        GoodDto goodDto = new GoodDto(1L, "name", GoodType.LAPTOP, 10);
        Good good = new Good();
        when(goodMapper.fromDto(goodDto)).thenReturn(good);
        when(goodRepository.save(good)).thenReturn(good);
        when(goodMapper.toDto(good)).thenReturn(goodDto);
        assertEquals(goodDto, goodService.save(goodDto));
    }

    @Test
    public void testUpdate() {
        GoodDto goodDto = new GoodDto(1L, "name", GoodType.LAPTOP, 10);
        Good good = new Good();
        when(goodMapper.fromDto(goodDto)).thenReturn(good);
        when(goodRepository.save(good)).thenReturn(good);
        when(goodMapper.toDto(good)).thenReturn(goodDto);
        assertEquals(goodDto, goodService.update(1L, goodDto));
    }

    @Test
    public void testDelete() {
        when(goodRepository.existsById(1L)).thenReturn(true);
        boolean result = goodService.delete(1L);
        assertTrue(result);
        verify(goodRepository, times(1)).deleteById(1L);
    }

}

