package com.backend_API.Yarah.customerseller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerService {
private final CustomerSellerProfileRepository customerRepository;

    public CustomerSellerProfile createCustomer(CustomerSellerProfile customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new IllegalStateException("Email already registered");
        }
        return customerRepository.save(customer);
    }

    public CustomerSellerProfile updateCustomer(Long id, CustomerSellerProfile customerDetails) {
        CustomerSellerProfile customer = customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        customer.setShippingAddress(customerDetails.getShippingAddress());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());

        return customerRepository.save(customer);
    }

    public CustomerSellerProfile getCustomerById(Long id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    public List<CustomerSellerProfile> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<CustomerSellerProfile> searchByAddress(String address) {
        return customerRepository.findByShippingAddressContaining(address);
    }

    public List<CustomerSellerProfile> searchByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumberContaining(phoneNumber);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found");
        }
        customerRepository.deleteById(id);
    }
}
