package br.com.compasso.mscustomers.dtos;

import br.com.compasso.mscustomers.entites.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO implements Serializable {

    private UUID id;
    private String firstName;
    private String lastName;
    private String fullName;
    private Timestamp birthdate;
    private String email;
    private String cpf;
    private String rg;
    private LocalDateTime registerDate;
    private String phone;
    private List<Address> addresses;
}
