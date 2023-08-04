package com.toy4.domain.dayoff.repository;

import com.toy4.domain.dayoff.domain.DayOff;
import com.toy4.domain.dayoff.type.DayOffType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;

@DataJpaTest
class DayOffRepositoryTest {

    @Autowired
    private DayOffRepository dayOffRepository;

    @Test
    void findByType() {
        Arrays.stream(DayOffType.values())
                .forEach(type -> {
                    DayOff dayOff = dayOffRepository.findByType(type);
                    Assertions.assertThat(dayOff.getType()).isSameAs(type);
                });
    }
}