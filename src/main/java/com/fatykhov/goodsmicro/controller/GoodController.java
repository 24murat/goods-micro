package com.fatykhov.goodsmicro.controller;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<GoodDto>> getAllGoods() {
        List<GoodDto> goods = goodService.findAll();
        return new ResponseEntity<>(goods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GoodDto> getGoodById(@PathVariable Long id) {
        GoodDto good = goodService.findOne(id);
        return good != null ?
                new ResponseEntity<>(good, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<GoodDto> createGood(@RequestBody GoodDto goodDto) {
        GoodDto createdGood = goodService.save(goodDto);
        return new ResponseEntity<>(createdGood, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GoodDto> updateGood(@PathVariable Long id, @RequestBody GoodDto updatedGoodDto) {
        GoodDto updatedGood = goodService.update(id, updatedGoodDto);
        return updatedGood != null ?
                new ResponseEntity<>(updatedGood, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGood(@PathVariable Long id) {
        boolean isDeleted = goodService.delete(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
