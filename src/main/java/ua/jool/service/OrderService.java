package ua.jool.service;

import ua.jool.domain.OrderDTO;

import java.util.List;

public interface OrderService {

    void create(OrderDTO orderDto);

    List<OrderDTO> getAllUserOrders(Long id);

    List<OrderDTO> findAll();

}
