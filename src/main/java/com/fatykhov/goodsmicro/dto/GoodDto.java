package com.fatykhov.goodsmicro.dto;

import com.fatykhov.goodsmicro.entity.GoodType;

public record GoodDto(Long id, String name, GoodType type, Integer quantity) {
}
