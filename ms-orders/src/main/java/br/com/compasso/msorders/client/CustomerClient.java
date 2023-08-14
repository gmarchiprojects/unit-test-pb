package br.com.compasso.msorders.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(url = "${services.ms-customer.url}", name = "customer")
public interface CustomerClient {

    @GetMapping(value = "/{cpf}", produces = MediaType.APPLICATION_JSON_VALUE)
    void findCustomerByCpf(@PathVariable("cpf") String cpf);

}
