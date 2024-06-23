package com.Ravicomputer.ecommerce.Services;

import com.Ravicomputer.ecommerce.Model.OrderModel;
import com.Ravicomputer.ecommerce.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public ResponseEntity<OrderModel> addOrder(OrderModel orderModel){
        return new ResponseEntity<>(orderRepository.save(orderModel), HttpStatus.CREATED);
    }
    public List<OrderModel> getByUserId(long id){
        return orderRepository.findByUserId(id);
    }
}
