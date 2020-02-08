package com.ebi.person.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@Table(name = "hobby", schema = "PUBLIC")
public class Hobby {
  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @RestResource(exported = false)
  @JsonIgnore
  @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
  @JoinColumn(name = "person_id", updatable = false, nullable = false)
  private Person person;

  @Column(nullable = false)
  private String name;
}
