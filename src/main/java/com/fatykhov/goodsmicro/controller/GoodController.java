package com.fatykhov.goodsmicro.controller;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodController {
    private final GoodService goodService;

    @Autowired
    public GoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping
    public List<GoodDto> getAllGoods() {
        return goodService.findAll();
    }

    @GetMapping("/{id}")
    public GoodDto getGoodById(@PathVariable Long id) {
        return goodService.findById(id);
    }

    @PostMapping
    public GoodDto createGood(@RequestBody GoodDto goodDto) {
        return goodService.save(goodDto);
    }

    @PutMapping("/{id}")
    public GoodDto updateGood(@PathVariable Long id, @RequestBody GoodDto updatedGoodDto) {
        return goodService.update(id, updatedGoodDto);
    }

    @DeleteMapping("/{id}")
    public void deleteGood(@PathVariable Long id) {
        goodService.delete(id);
    }
}
