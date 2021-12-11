package br.com.dosanjosdeveloper.personapi.servive;

import br.com.dosanjosdeveloper.personapi.dto.MessageResponseDTO;
import br.com.dosanjosdeveloper.personapi.entity.Person;
import br.com.dosanjosdeveloper.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository pesPersonRepository) {
        this.personRepository = pesPersonRepository;
    }

    public MessageResponseDTO createPerson( Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID "+savedPerson.getId())
                .build();
    }
}
