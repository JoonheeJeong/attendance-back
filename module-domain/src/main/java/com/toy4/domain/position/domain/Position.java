package com.toy4.domain.position.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.position.type.PositionType;
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
public class Position extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private PositionType type;

}
