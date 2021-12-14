package br.com.dosanjosdeveloper.personapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;

    @NotEmpty
    @Size(min=2,max=100)
    private String firstName;

    @NotEmpty
    @Size(min=2,max=100)
    private String lastName;

    @NotEmpty
    @CPF
    private String cpf;


    private String birtDate;

    @Valid
    @NotEmpty
    private List<PhoneDTO> phones;
}
