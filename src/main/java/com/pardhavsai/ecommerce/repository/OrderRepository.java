package com.pardhavsai.ecommerce.repository;
import com.pardhavsai.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OrderRepository extends JpaRepository<Order, Long> { List<Order> findByUserId(Long userId); }
