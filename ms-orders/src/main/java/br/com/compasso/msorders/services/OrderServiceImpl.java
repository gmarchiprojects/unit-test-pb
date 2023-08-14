package br.com.compasso.msorders.services;

import br.com.compasso.msorders.client.CustomerClient;
import br.com.compasso.msorders.constants.ErrorConstants;
import br.com.compasso.msorders.dtos.OrderDTO;
import br.com.compasso.msorders.entities.Order;
import br.com.compasso.msorders.enums.ErrorCodes;
import br.com.compasso.msorders.exceptions.CustomerNotFoundException;
import br.com.compasso.msorders.exceptions.ExceptionResponse;
import br.com.compasso.msorders.exceptions.OrderNotFoundException;
import br.com.compasso.msorders.repositories.OrderRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ModelMapper modelMapper;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("OrderServiceImpl.createOrder - Start - Order: {}", orderDTO);
        try{
            customerClient.findCustomerByCpf(orderDTO.getCustomerCpf());
        }catch (FeignException e){
            log.error("OrderServiceImpl.createOrder - Error - Customer Not Found CPF: {}, please register customer before continue", orderDTO.getCustomerCpf());
            throw new CustomerNotFoundException(
                    new ExceptionResponse(ErrorCodes.CUSTOMER_NOT_FOUND, ErrorConstants.CUSTOMER_NOT_FOUND));
        }
        Order order = modelMapper.map(orderDTO, Order.class);
        return modelMapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public OrderDTO findOrderById(String uuid) {
        log.info("OrderServiceImpl.findOrderById - Start - orderId: {}", uuid);
        Order order = orderRepository.findById(UUID.fromString(uuid)).orElseThrow(
                () -> new OrderNotFoundException(
                        new ExceptionResponse(ErrorCodes.ORDER_NOT_FOUND, ErrorConstants.ORDER_NOT_FOUND))
        );
        log.info("OrderServiceImpl.findOrderById - End");
        return modelMapper.map(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findOrdersByCpf(String cpf) {
        List<Order> orders = orderRepository.findOrdersByCustomerCpf(cpf).orElseThrow(
                () -> new OrderNotFoundException(
                        new ExceptionResponse(ErrorCodes.ORDER_NOT_FOUND, ErrorConstants.ORDER_NOT_FOUND)
                ));

        return orders.stream().map(order ->
                modelMapper.map(order, OrderDTO.class))
                .collect(toList());
    }
}
