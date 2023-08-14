package br.com.compasso.mscustomers.services;

import br.com.compasso.mscustomers.constants.ErrorConstants;
import br.com.compasso.mscustomers.dtos.CustomerDTO;
import br.com.compasso.mscustomers.entites.Customer;
import br.com.compasso.mscustomers.enums.ErrorCodes;
import br.com.compasso.mscustomers.exceptions.CustomerNotFoundException;
import br.com.compasso.mscustomers.exceptions.DuplicatedCpfException;
import br.com.compasso.mscustomers.exceptions.ExceptionResponse;
import br.com.compasso.mscustomers.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("CustomerServiceImpl.createCustomer - Start - Customer: {}", customerDTO);

        if(customerRepository.findCustomerByCpf(customerDTO.getCpf()).isPresent()){
            log.info("CustomerServiceImpl.createCustomer - Customer Already Exists");
            throw new DuplicatedCpfException(
                    new ExceptionResponse(ErrorCodes.BAD_REQUEST, ErrorConstants.CUSTOMER_ALREADY_EXISTS));
        }else {
            log.info("CustomerServiceImpl.createCustomer - Customer Not Found - Saving Customer...");
            Customer customer = modelMapper.map(customerDTO, Customer.class);
            return modelMapper.map(customerRepository.save(customer), CustomerDTO.class);
        }

    }

    @Override
    public CustomerDTO findCustomerByCpf(String cpf) {
        log.info("CustomerServiceImpl.findCustomerByCpf - Start - CPF: {}", cpf);
        if(customerRepository.findCustomerByCpf(cpf).isPresent()){
            log.info("CustomerServiceImpl.findCustomerByCpf - Customer Found");
            return modelMapper.map(customerRepository.findCustomerByCpf(cpf).orElse(new Customer()), CustomerDTO.class);
        }else {
            log.info("CustomerServiceImpl.findCustomerByCpf - Customer Not Found - CPF: {}", cpf);
            throw new CustomerNotFoundException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_NOT_FOUND, ErrorConstants.CUSTOMER_NOT_FOUND));
        }
    }
}
