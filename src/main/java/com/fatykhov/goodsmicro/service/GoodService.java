package com.fatykhov.goodsmicro.service;

import com.fatykhov.goodsmicro.dto.GoodDto;
import com.fatykhov.goodsmicro.entity.Good;
import com.fatykhov.goodsmicro.mapper.GoodMapper;
import com.fatykhov.goodsmicro.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        return goodMapper.toDtoList(goods);
    }

    public GoodDto findById(Long id) {
        Optional<Good> existingGood = goodRepository.findById(id);
        if (existingGood.isPresent()) {
            Good good = existingGood.get();
            return goodMapper.toDto(good);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Good not found");
        }
    }

    @Transactional
    public GoodDto save(GoodDto goodDto) {
        Optional<Good> existingGood = goodRepository.findByNameAndType(goodDto.name(), goodDto.type());
        if (existingGood.isPresent()) {
            Good good = existingGood.get();
            good.setQuantity(good.getQuantity() + goodDto.quantity());
            Good savedGood = goodRepository.save(good);
            return goodMapper.toDto(savedGood);
        } else {
            Good good = goodMapper.fromDto(goodDto);
            Good savedGood = goodRepository.save(good);
            return goodMapper.toDto(savedGood);
        }
    }

    @Transactional
    public GoodDto update(Long id, GoodDto updatedGoodDto) {
        Good updatedGood = goodMapper.fromDto(updatedGoodDto);
        updatedGood.setId(id);
        goodRepository.save(updatedGood);
        return goodMapper.toDto(updatedGood);
    }

    @Transactional
    public boolean delete(Long id) {
        if (goodRepository.existsById(id)) {
            goodRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
