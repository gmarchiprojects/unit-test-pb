package br.com.compasso.mscustomers.controllers;

import br.com.compasso.mscustomers.dtos.CustomerDTO;
import br.com.compasso.mscustomers.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> searchCustomerByCpf(@PathVariable("cpf") String cpf){
        log.info("CustomerControllers.searchCustomerByCpf - Start - CPF: {}", cpf);
        CustomerDTO customer = customerService.findCustomerByCpf(cpf);
        log.info("CustomerControllers.searchCustomerByCpf - End");
        return ResponseEntity.ok(customer);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        log.info("CustomerControllers.createCustomer - Start - Customer: {}", customerDTO);
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        log.info("CustomerControllers.createCustomer - End");
        return ResponseEntity.ok(customer);
    }
}
