package com.mia.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void lombokTest() {
        //given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);
        System.out.println(dto.getAmount() + dto.getName());

        // then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertThat : assertj 라는 테스트 검증 라이브러리의 검증 메소드. 검증하고 싶은 대상을 메소드 인자로 받음

    }
}
