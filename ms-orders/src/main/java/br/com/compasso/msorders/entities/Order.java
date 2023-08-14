package br.com.compasso.msorders.entities;

import br.com.compasso.msorders.enums.PaymentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_01_ORDER")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "customer_cpf")
    private String customerCpf;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Set<OrderItem> productsOrder;
    @Column(name = "payment_type")
    private PaymentTypeEnum paymentType;
    @Column(name = "create_date")
    private LocalDate createDate;
    @Column(name = "delivery_date")
    private LocalDate deliveryDate;
}
