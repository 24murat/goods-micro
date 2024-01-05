package com.fatykhov.goodsmicro.service;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import com.fatykhov.goodsmicro.mapper.GoodMapper;
import com.fatykhov.goodsmicro.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class GoodService {
    private final GoodRepository goodRepository;
    private final GoodMapper goodMapper;

    @Autowired
    public GoodService(GoodRepository goodRepository, GoodMapper goodMapper) {
        this.goodRepository = goodRepository;
        this.goodMapper = goodMapper;
    }

    public List<GoodDto> findAll() {
        List<Good> goods = goodRepository.findAll();
        return goods.stream()
                .map(goodMapper::toDto)
                .toList();
    }

    public GoodDto findOne(Long id) {
        Optional<Good> foundGood = goodRepository.findById(id);
        return foundGood.map(goodMapper::toDto).orElse(null);
    }

    @Transactional
    public GoodDto save(GoodDto goodDto) {
        Good good = goodRepository.save(goodMapper.fromDto(goodDto));
        return goodMapper.toDto(good);
    }

    @Transactional
    public GoodDto update(Long id, GoodDto updatedGoodDto) {
        Good updatedGood = goodMapper.fromDto(updatedGoodDto);
        updatedGood.setId(id);
        goodRepository.save(updatedGood);
        return goodMapper.toDto(updatedGood);
    }

    @Transactional
    public void delete(Long id) {
        goodRepository.deleteById(id);
    }

}
