package br.com.compasso.mscustomers.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "TB_01_CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "full_name")
    private String fullName = String.format("%s %s", firstName, lastName);
    @Column(name = "birthdate")
    private Timestamp birthdate;
    @Column(name = "email")
    private String email;
    @Column(name = "cpf", unique = true)
    private String cpf;
    @Column(name = "rg", nullable = false)
    private String rg;
    @Column(name = "register_date")
    @CreationTimestamp
    private Timestamp registerDate;
    @Column(name = "update_date")
    @UpdateTimestamp
    private Timestamp updateDate;
    @Column(name = "phone")
    private String phone;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;


}
