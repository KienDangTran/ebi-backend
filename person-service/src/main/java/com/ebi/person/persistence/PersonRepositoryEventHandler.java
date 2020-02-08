package com.ebi.person.persistence;

import com.ebi.person.model.Person;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class PersonRepositoryEventHandler {

  @HandleBeforeCreate
  @HandleBeforeSave
  public void handleBefore(Person person){
    if(!person.getHobbies().isEmpty()) {
      person.getHobbies().forEach(hobby -> hobby.setPerson(person));
    }
  }
}
