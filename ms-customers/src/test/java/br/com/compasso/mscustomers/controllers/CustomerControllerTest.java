package br.com.compasso.mscustomers.controllers;

import br.com.compasso.mscustomers.dtos.CustomerDTO;
import br.com.compasso.mscustomers.exceptions.DuplicatedCpfException;
import br.com.compasso.mscustomers.services.CustomerService;
import br.com.compasso.mscustomers.utils.JsonUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    private MockMvc mockMvc;
    private static final String CUSTOMER = "payloads/customers/customer.json";
    private static final String STATIC_CPF = "12345678911";

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
    @Test
    void searchCustomerByCpf() throws IOException {

    }

    @Test
    void createCustomer() throws Exception {
        //given
        String payload = JsonUtils.readFileAsString(CUSTOMER);
        CustomerDTO customerDTO = JsonUtils.getObjectFromFile(CUSTOMER, CustomerDTO.class);
        when(customerService.createCustomer(any())).thenReturn(customerDTO);
        //then
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/customer/")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createCustomerDuplicatedCpf() throws Exception {
        String payload = JsonUtils.readFileAsString(CUSTOMER);
        when(customerService.createCustomer(any())).thenThrow(DuplicatedCpfException.class);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/v1/customer/")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}