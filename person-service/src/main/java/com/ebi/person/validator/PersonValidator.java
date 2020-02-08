package com.ebi.person.validator;

import com.ebi.person.model.Person;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
  @Override public boolean supports(Class<?> clazz) {
    return Person.class.isAssignableFrom(clazz);
  }

  @Override public void validate(Object target, Errors errors) {
    validateRequiredField(errors);
    if (!errors.hasErrors()) {
      Person person = (Person) target;
      validateAge(person, errors);
    }
  }

  private void validateAge(Person person, Errors errors) {
    if (person.getAge() != null && person.getAge() < 0) {
      errors.rejectValue(
          "age",
          "Age must be greater than 0",
          new Object[] {},
          "Age must be greater than 0"
      );
    }
  }

  private void validateRequiredField(Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors,
        "firstName",
        "First name cannot be blank",
        new Object[] {},
        "First name cannot be blank"
    );
    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors,
        "lastName",
        "Last name cannot be blank",
        new Object[] {},
        "Last name cannot be blank"
    );
  }

  @Component("beforeCreatePersonValidator")
  public static class BeforeCreatePersonValidator extends PersonValidator {}

  @Component("beforeSavePersonValidator")
  public static class BeforeSavePersonValidator extends PersonValidator {}
}
