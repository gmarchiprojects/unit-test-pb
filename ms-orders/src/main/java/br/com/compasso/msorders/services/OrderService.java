package br.com.compasso.msorders.services;

import br.com.compasso.msorders.dtos.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO findOrderById(String uuid);
    List<OrderDTO> findOrdersByCpf(String cpf);

}
