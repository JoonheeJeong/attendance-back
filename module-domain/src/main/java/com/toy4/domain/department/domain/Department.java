package com.toy4.domain.department.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.department.type.DepartmentType;
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
public class Department extends BaseEntity {

  @Enumerated(EnumType.STRING)
  private DepartmentType type;

}
