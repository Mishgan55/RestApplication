package khorsun.spring.FirstRestApplication.controller;

import khorsun.spring.FirstRestApplication.DTO.PersonDTO;
import khorsun.spring.FirstRestApplication.models.Person;
import khorsun.spring.FirstRestApplication.services.PersonService;
import khorsun.spring.FirstRestApplication.utils.PersonErrorResponse;
import khorsun.spring.FirstRestApplication.utils.PersonNotCreatedException;
import khorsun.spring.FirstRestApplication.utils.PersonNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PersonService personService;
    private final ModelMapper modelMapper;
    @Autowired
    public PeopleController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<PersonDTO> findPeople(){
        return personService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonDTO findPersonById(@PathVariable("id") int id){
        return convertToPersonDTO(personService.findOne(id));
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handelException(PersonNotFoundException e){

        PersonErrorResponse response = new PersonErrorResponse("This person doesn't exist", new Date());

        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> savePerson(@RequestBody @Valid PersonDTO personDTO,
                                                 BindingResult bindingResult){

        if (bindingResult.hasErrors()){

            StringBuilder stringBuilder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(fieldError.getField()).append(" - ").append(fieldError.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedException(stringBuilder.toString());
        }

        personService.save(convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);

    }

    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO,Person.class);
    }
    private PersonDTO convertToPersonDTO(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handelException(PersonNotCreatedException e){

        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(), new Date());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }



}
