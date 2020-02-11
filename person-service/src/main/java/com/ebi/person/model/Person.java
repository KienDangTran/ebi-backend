package com.ebi.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = {"hobbies"})
@Entity
@Table(name = "person", schema = "PUBLIC")
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  private Integer age;

  private String favouriteColour;

  @OneToMany(cascade = CascadeType.ALL,
             mappedBy = "person",
             fetch = FetchType.LAZY,
             orphanRemoval = true)
  private Set<Hobby> hobbies = new HashSet<>();
}
