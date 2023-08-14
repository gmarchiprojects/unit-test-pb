package br.com.compasso.mscustomers.services;

import br.com.compasso.mscustomers.dtos.CustomerDTO;
import br.com.compasso.mscustomers.entites.Customer;
import br.com.compasso.mscustomers.exceptions.DuplicatedCpfException;
import br.com.compasso.mscustomers.repositories.CustomerRepository;
import br.com.compasso.mscustomers.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Spy
    private ModelMapper modelMapper;

    private static final String CUSTOMER = "payloads/customers/customer.json";
    private static final String STATIC_CPF = "12345678911";

    @Test
    void createCustomer() throws IOException {
        //given
        CustomerDTO customerDTO = JsonUtils.getObjectFromFile(CUSTOMER, CustomerDTO.class);
        Customer customer = JsonUtils.getObjectFromFile(CUSTOMER, Customer.class);
        when(customerRepository.findCustomerByCpf(any())).thenReturn(Optional.empty());
        when(customerRepository.save(any())).thenReturn(customer);
        //then
        CustomerDTO response = customerService.createCustomer(customerDTO);
        //verify
        assertEquals(STATIC_CPF, response.getCpf());
    }

    @Test
    void createCustomerDuplicatedCpfException() throws IOException {
        //given
        CustomerDTO customerDTO = JsonUtils.getObjectFromFile(CUSTOMER, CustomerDTO.class);
        Customer customer = JsonUtils.getObjectFromFile(CUSTOMER, Customer.class);
        when(customerRepository.findCustomerByCpf(any())).thenReturn(Optional.of(customer));
        //then
        assertThrows(DuplicatedCpfException.class,
                () -> customerService.createCustomer(customerDTO)
        );
    }

    @Test
    void findCustomerByCpf() {

    }
}