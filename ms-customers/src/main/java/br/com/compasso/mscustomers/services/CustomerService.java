package br.com.compasso.mscustomers.services;

import br.com.compasso.mscustomers.dtos.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customer);
    CustomerDTO findCustomerByCpf(String cpf);
}
