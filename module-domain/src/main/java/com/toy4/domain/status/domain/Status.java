package com.toy4.domain.status.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.status.type.StatusType;
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
public class Status extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private StatusType type;

}
