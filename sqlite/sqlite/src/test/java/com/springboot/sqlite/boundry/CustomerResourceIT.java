package com.springboot.sqlite.boundry;

import com.springboot.sqlite.Entities.Customer;
import com.springboot.sqlite.Repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@DirtiesContext
public class CustomerResourceIT {
    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @MockBean
    private CustomerRepository customerRepository;


    @BeforeEach
    public void init(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception{

        List<Customer> retrievedCustomers = new ArrayList<>();
        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setName("customer1");
        customer1.setPhone("019876554");
        retrievedCustomers.add(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setName("customer2");
        customer2.setPhone("123456789");
        retrievedCustomers.add(customer2);

        Mockito.when(customerRepository.findAll()).thenReturn(retrievedCustomers);

        MvcResult mvcResult = mvc.perform(
                MockMvcRequestBuilders.get("/customers")).andExpect(status().isOk()).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        assertEquals(200, mockHttpServletResponse.getStatus());

        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"id\":" + customer1.getId()));
        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"name\":\"" + customer1.getName() + "\""));
        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"phone\":\"" + customer1.getPhone() + "\""));

        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"id\":" + customer2.getId()));
        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"name\":\"" + customer2.getName() + "\""));
        assertThat(mockHttpServletResponse.getContentAsString(), containsString("\"phone\":\"" + customer2.getPhone() + "\""));

    }
}
