package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private Set<Customer> customers = new HashSet<>();
    private static CustomerService singleInstance = new CustomerService();
    private CustomerService() {

    }
    public void addCustomer(String email, String firstName, String lastName ) throws Exception {
        Customer newCustomer = new Customer(firstName,lastName,email);
        if (customers.contains(newCustomer)) {
            throw new Exception("Email already in use");
        }else {
            customers.add(newCustomer);

        }

    }
    public Customer getCustomer(String customerEmail) throws Exception {
        Map<String,Customer> customerMap = new HashMap<>();
        for(Customer customer:customers){
            customerMap.put(customer.getEmail(),customer);
        }
        if (customerMap.get(customerEmail) == null){
            throw new Exception("Customer not registered");
        }
        else {
            return customerMap.get(customerEmail);

        }

    }

    public Collection<Customer> getAllCustomers(){
        return customers;
    }

    public static synchronized CustomerService getInstance(){
        return singleInstance;
    }
}
