package com.springboot.sqlite.Repositories;

import com.springboot.sqlite.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Integer>{

}
