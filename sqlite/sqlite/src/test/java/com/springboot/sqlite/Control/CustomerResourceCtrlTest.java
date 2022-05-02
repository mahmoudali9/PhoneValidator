package com.springboot.sqlite.Control;

import com.springboot.sqlite.Entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext
public class CustomerResourceCtrlTest {

    private CustomerResourceCtrl customerResourceCtrl;

    @BeforeEach
    public void init(){
        customerResourceCtrl = new CustomerResourceCtrl();
    }

    @Test
    public void testSetCustomerCountryAndCountryCodeAndState(){
        Customer initialCustomer = new Customer();
        initialCustomer.setId(1);
        initialCustomer.setName("customerName");
        initialCustomer.setPhone("(251) 924418461");

        customerResourceCtrl.setCustomerCountryAndCountryCodeAndState(initialCustomer);

        assertEquals("Ethiopia", initialCustomer.getCountry());

    }

    @Test
    public void testFilterCustomerByCountry(){
        String country = "Ethiopia";
        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customerName");
        customer1.setPhone("(251) 924418461");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("customerName");
        customer2.setPhone("(251) 924418461");
        customerList.add(customer2);

        List<Customer> result = customerResourceCtrl.filterCustomerByCountryAndState(country, null, customerList);

        assertEquals(2, result.size());
    }

    @Test
    public void testFilterCustomerByState(){
        Boolean state = true;
        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customerName");
        customer1.setPhone("(251) 924418461");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("customerName");
        customer2.setPhone("(251) 924418461");
        customerList.add(customer2);

        Customer customer3 = new Customer();
        customer3.setId(1);
        customer3.setName("customerName");
        customer3.setPhone("(237) 6A0311634");
        customerList.add(customer3);

        List<Customer> result = customerResourceCtrl.filterCustomerByCountryAndState(null, state, customerList);

        assertEquals(2, result.size());
    }

    @Test
    public void testFilterCustomerByCountryState(){
        String country = "Uganda";
        Boolean state = true;
        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customerName");
        customer1.setPhone("(256) 775069443");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("customerName");
        customer2.setPhone("(251) 924418461");
        customerList.add(customer2);

        Customer customer3 = new Customer();
        customer3.setId(1);
        customer3.setName("customerName");
        customer3.setPhone("(237) 6A0311634");
        customerList.add(customer3);

        List<Customer> result = customerResourceCtrl.filterCustomerByCountryAndState(country, state, customerList);

        assertEquals(1, result.size());
    }
    @Test
    public void testNoFilterCustomer(){
        List<Customer> customerList = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customerName");
        customer1.setPhone("(256) 775069443");
        customerList.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(1);
        customer2.setName("customerName");
        customer2.setPhone("(251) 924418461");
        customerList.add(customer2);

        Customer customer3 = new Customer();
        customer3.setId(1);
        customer3.setName("customerName");
        customer3.setPhone("(237) 6A0311634");
        customerList.add(customer3);

        List<Customer> result = customerResourceCtrl.filterCustomerByCountryAndState(null, null, customerList);

        assertEquals(3, result.size());
    }
}
