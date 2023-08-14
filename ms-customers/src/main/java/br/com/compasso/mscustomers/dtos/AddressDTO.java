package br.com.compasso.mscustomers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private UUID id;
    private String address;
    private String number;
    private String neighborhood;
    private String postalCode;
    private String city;
    private String state;
}
