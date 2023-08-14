package br.com.compasso.msorders.services;

import br.com.compasso.msorders.client.CustomerClient;
import br.com.compasso.msorders.dtos.OrderDTO;
import br.com.compasso.msorders.entities.Order;
import br.com.compasso.msorders.exceptions.CustomerNotFoundException;
import br.com.compasso.msorders.repositories.OrderRepository;
import br.com.compasso.msorders.utils.JsonUtils;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private CustomerClient customerClient;
    @Spy
    private ModelMapper modelMapper;

    private static final String ORDER = "payloads/orderDTO.json";
    private static final String ORDER_DTO = "payloads/order.json";
    private static final String STATIC_CPF = "123456";

    @Test
    void createOrder() throws IOException {
        //given
        OrderDTO orderDTO = JsonUtils.getObjectFromFile(ORDER_DTO, OrderDTO.class);
        Order order = JsonUtils.getObjectFromFile(ORDER, Order.class);
        doNothing().when(customerClient).findCustomerByCpf(any());
        when(orderRepository.save(any())).thenReturn(order);
        //then
        OrderDTO response = orderService.createOrder(orderDTO);
        //verify
        verify(customerClient, times(1)).findCustomerByCpf(any());
        assertEquals(STATIC_CPF, response.getCustomerCpf());
    }

    @Test
    void createOrderFeignClient() throws Exception {
        //given
        OrderDTO orderDTO = JsonUtils.getObjectFromFile(ORDER_DTO, OrderDTO.class);
        doThrow(FeignException.class).when(customerClient).findCustomerByCpf(any());
        //then
        assertThrows(CustomerNotFoundException.class, () -> {
            orderService.createOrder(orderDTO);
        });
        //verify
        verify(customerClient, times(1)).findCustomerByCpf(any());
    }
}