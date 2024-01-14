package com.fatykhov.goodsmicro.controller;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.service.GoodService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GoodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GoodService goodService;

    private GoodDto goodDto;

    @BeforeEach
    public void setup() {
        goodDto = new GoodDto(1L, "Test Good", "Test Type", 10);
    }

    @Test
    public void getAllGoodsTest() throws Exception {
        List<GoodDto> goods = Collections.singletonList(goodDto);
        when(goodService.findAll()).thenReturn(goods);

        mockMvc.perform(get("/goods")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getGoodByIdTest() throws Exception {
        when(goodService.findById(anyLong())).thenReturn(goodDto);

        mockMvc.perform(get("/goods/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createGoodTest() throws Exception {
        when(goodService.save(any(GoodDto.class))).thenReturn(goodDto);

        mockMvc.perform(post("/goods")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Test Good\",\"type\":\"Test Type\",\"quantity\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateGoodTest() throws Exception {
        when(goodService.update(anyLong(), any(GoodDto.class))).thenReturn(goodDto);

        mockMvc.perform(put("/goods/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Test Good\",\"type\":\"Test Type\",\"quantity\":10}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteGoodTest() throws Exception {
        when(goodService.delete(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/goods/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

