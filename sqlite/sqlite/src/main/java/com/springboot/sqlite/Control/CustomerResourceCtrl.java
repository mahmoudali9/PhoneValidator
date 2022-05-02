package com.springboot.sqlite.Control;

import com.springboot.sqlite.Entities.Customer;
import javafx.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerResourceCtrl {

    public void setCustomerCountryAndCountryCodeAndState(Customer customer){
        List<Pair<String, Pair<String,String>>> regexList = new ArrayList<>();
        customer.setState(false);
        regexList.add(new Pair<>("Cameroon", new Pair<>("\\(237\\)\\ ?[2368]\\d{7,8}$","(237)")));
        regexList.add(new Pair<>("Ethiopia", new Pair<>("\\(251\\)\\ ?[1-59]\\d{8}$","(251)")));
        regexList.add(new Pair<>("Morocco",  new Pair<>("\\(212\\)\\ ?[5-9]\\d{8}$","(212)")));
        regexList.add(new Pair<>("Mozambique", new Pair<>("\\(258\\)\\ ?[28]\\d{7,8}$","(258)")));
        regexList.add(new Pair<>("Uganda", new Pair<>("\\(256\\)\\ ?\\d{9}$","(256)")));


        customer.setCountryCode("+"+customer.getPhone().substring(customer.getPhone().indexOf('(')+1,customer.getPhone().indexOf(')')));

        for (Pair<String,Pair<String,String>> stringStringPair : regexList) {
            if (customer.getPhone().matches(stringStringPair.getValue().getKey())) {
                customer.setCountry(stringStringPair.getKey());
//                customer.setCountryCode(stringStringPair.getValue().getValue());
                customer.setState(true);
                break;
            }
        }
    }


    public List<Customer> filterCustomerByCountryAndState(String country, Boolean state, List<Customer> customers) {
        List<Customer> updatedCustomers = new ArrayList<>();
        customers.forEach(
                customer -> {
                    setCustomerCountryAndCountryCodeAndState(customer);
                    if (country == null && state == null) {
                        updatedCustomers.add(customer);
                    } else if (country != null && state == null) {
                        if (country.equals(customer.getCountry())) {
                            updatedCustomers.add(customer);
                        }
                    } else if (country == null && state != null) {
                        if (state.equals(customer.getState())) {
                            updatedCustomers.add(customer);
                        }
                    } else {
                        if (country.equals(customer.getCountry()) && state.equals(customer.getState())) {
                            updatedCustomers.add(customer);
                        }
                    }
                }
        );

        return updatedCustomers;
    }
}
