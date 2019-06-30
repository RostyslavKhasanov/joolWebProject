package ua.jool.service.impl;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ua.jool.domain.OrderDTO;
import ua.jool.entity.OrderEntity;
import ua.jool.repository.OrderRepository;
import ua.jool.service.OrderService;
import ua.jool.utils.ObjectMapperUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapperUtils modelMapper;

    @Override
    public void create(OrderDTO orderDto) {
        orderDto.setDate(LocalDate.now());
        OrderEntity orderEntity = modelMapper.map(orderDto, OrderEntity.class);
        orderRepository.save(orderEntity);
    }

    @Override
    public List<OrderDTO> getAllUserOrders(Long id) {
        List<OrderDTO> orders = modelMapper.mapAll(orderRepository.findAllByUser_Id(id), OrderDTO.class);
        return orders;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<OrderDTO> orderDtos = modelMapper.mapAll(orderEntities, OrderDTO.class);
        return orderDtos;
    }
}
