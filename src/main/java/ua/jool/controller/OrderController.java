package ua.jool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.jool.domain.OrderDTO;
import ua.jool.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO) {
        orderService.create(orderDTO);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAllUserOrders(@PathVariable("id") Long id) {
        List<OrderDTO> orderDtos = orderService.getAllUserOrders(id);
        return new ResponseEntity<List<OrderDTO>>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers() {
        List<OrderDTO> orderDtos = orderService.findAll();
        return new ResponseEntity<List<OrderDTO>>(orderDtos, HttpStatus.OK);
    }
}
