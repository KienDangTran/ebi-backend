import com.ebi.person.PersonServiceApplication;
import com.ebi.person.model.Person;
import com.ebi.person.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = PersonServiceApplication.class
)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class PersonApiIT {
  @Autowired private MockMvc mockMvc;

  @Autowired ObjectMapper objectMapper;

  @Autowired private PersonRepository personRepository;

  @Test
  public void personCRUDTest() throws Exception {
    // given
    Person person = new Person();
    person.setFirstName("John");
    person.setLastName("Wick");
    person.setAge(29);
    person.setFavouriteColour("black");

    mockMvc
        .perform(post("/persons")
            .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(objectMapper.writeValueAsBytes(person)))
        .andExpect(status().isCreated());

    mockMvc
        .perform(patch("/persons/1")
            .header("Authorization", "Basic dXNlcjpwYXNzd29yZA==")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content("{\"age\": 30}"))
        .andExpect(status().is2xxSuccessful());

    mockMvc
        .perform(get("/persons").header("Authorization", "Basic dXNlcjpwYXNzd29yZA=="))
        .andExpect(status().is2xxSuccessful());
  }
}
