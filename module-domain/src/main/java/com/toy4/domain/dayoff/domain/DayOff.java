package com.toy4.domain.dayoff.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.dayoff.type.DayOffType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DayOff extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private DayOffType type;
    private Float amount;
}
