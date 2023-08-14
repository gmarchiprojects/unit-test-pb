package br.com.compasso.msorders.controllers;

import br.com.compasso.msorders.dtos.OrderDTO;
import br.com.compasso.msorders.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO){
        log.info("OrderController.createOrder - Start - Order: {}", orderDTO);
        OrderDTO order = orderService.createOrder(orderDTO);
        log.info("OrderController.createOrder - End");
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDTO> findOrderById(@PathVariable("uuid") String uuid){
        log.info("OrderController.findOrderById - Start - OrderId: {}", uuid);
        OrderDTO order = orderService.findOrderById(uuid);
        log.info("OrderController.findOrderById - End");
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/{cpf}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDTO>> findOrdersByCpf(@PathVariable("cpf") String cpf){
        log.info("OrderController.findOrdersByCpf - Start - CPF: {}", cpf);
        List<OrderDTO> order = orderService.findOrdersByCpf(cpf);
        log.info("OrderController.findOrdersByCpf - End");
        return ResponseEntity.ok(order);
    }
}
