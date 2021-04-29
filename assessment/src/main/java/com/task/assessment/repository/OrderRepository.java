package com.task.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task.assessment.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
