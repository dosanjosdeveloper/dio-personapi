package br.com.dosanjosdeveloper.personapi.servive;

import br.com.dosanjosdeveloper.personapi.dto.MessageResponseDTO;
import br.com.dosanjosdeveloper.personapi.dto.PersonDTO;
import br.com.dosanjosdeveloper.personapi.entity.Person;
import br.com.dosanjosdeveloper.personapi.exception.PersonNotFoundException;
import br.com.dosanjosdeveloper.personapi.mapper.PersonMapper;
import br.com.dosanjosdeveloper.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;
//
//    @Autowired
//    public PersonService(PersonRepository pesPersonRepository) {
//        this.personRepository = pesPersonRepository;
//    }

    public MessageResponseDTO createPerson( PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);


        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId(),"Created person with ID");
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
//        Optional<Person> optionalPerson = personRepository.findById(id);
        //        if (optionalPerson.isEmpty()){
//            throw new PersonNotFoundException(id);
//        }
        Person person = verifyIfExistis(id);

        return personMapper.toDTO(person);
    }

    private Person verifyIfExistis(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Long id) throws PersonNotFoundException {
//        personRepository.findById(id)
//                .orElseThrow(() -> new PersonNotFoundException(id));
        verifyIfExistis(id);
        personRepository.deleteById(id);
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExistis(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatePersom = personRepository.save(personToUpdate);
        return createMessageResponse(updatePersom.getId(),"Updated person with ID.");
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
