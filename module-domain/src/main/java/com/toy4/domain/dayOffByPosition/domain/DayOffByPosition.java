package com.toy4.domain.dayOffByPosition.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.position.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DayOffByPosition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    private Position position;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}