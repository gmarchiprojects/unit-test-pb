package br.com.compasso.mscustomers.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity(name = "TB_02_ADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "address")
    private String address;
    @Column(name = "number")
    private String number;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

}
