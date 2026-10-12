package com.chirag.bookmyshowbackend.service;

import com.chirag.bookmyshowbackend.dto.CreateProfileRequest;
import com.chirag.bookmyshowbackend.dto.ProfileResponse;
import com.chirag.bookmyshowbackend.entity.Customer;
import com.chirag.bookmyshowbackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProfileService {

    private final CustomerRepository customerRepository;

    public ProfileService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String normalizedPhone(String phone){

        String normalized = phone == null ? "" : phone.replaceAll("\\D","");

        if(normalized.length() != 10){
            throw new IllegalArgumentException("Phone number must be 10 digits");
        }

        return normalized;
    }

    public ProfileResponse create (CreateProfileRequest request){

        String email = request.email();
        String phone = request.phone();

        if(customerRepository.existsByEmail(email)){
            throw new ProfileConflictException("Email already exists");
        }

        if(customerRepository.existsByPhone(phone)){
            throw new ProfileConflictException("Phone number already exists");
        }

        return ProfileResponse.from(customerRepository.save(new Customer(request.name().trim(),email,phone)));

    }

    public ProfileResponse login(String identifier){
        String value = identifier == null ? " " : identifier.trim();
        String phone = normalizedPhone(value);
        Customer customer = value.contains("@") ? customerRepository.findByEmail(value.toLowerCase(Locale.ROOT)).orElse(null) : customerRepository.findByPhone(phone).orElse(null);

        if (customer==null){
            throw new ResourceNotFoundException("No profile is found for the given email or phone number");
        }

        return ProfileResponse.from(customer);
    }
}
