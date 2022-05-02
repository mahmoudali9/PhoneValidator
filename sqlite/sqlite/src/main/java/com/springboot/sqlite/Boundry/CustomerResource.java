package com.springboot.sqlite.Boundry;

import com.springboot.sqlite.Control.CustomerResourceCtrl;
import com.springboot.sqlite.Repositories.CustomerRepository;
import com.springboot.sqlite.Entities.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@CrossOrigin
@RequestMapping
public class CustomerResource {
    private final CustomerRepository customerRepository;
    private final CustomerResourceCtrl customerResourceCtrl;

    @Autowired
    public CustomerResource(CustomerRepository customerRepository, CustomerResourceCtrl customerResourceCtrl) {
        this.customerRepository = customerRepository;
        this.customerResourceCtrl = customerResourceCtrl;
    }

    @ApiOperation(value = "GET", response = Customer.class, responseContainer = "List")
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam(value = "country",required = false) String country,
            @RequestParam(value =  "state" ,required = false) Boolean state
    ){

        List<Customer> retrievedCustomers = customerRepository.findAll();
        List<Customer> filteredCustomers = customerResourceCtrl.filterCustomerByCountryAndState(country, state, retrievedCustomers);
        return ResponseEntity.ok().body(filteredCustomers);
    }

}
