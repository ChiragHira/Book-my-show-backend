package com.chirag.bookmyshowbackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers",uniqueConstraints = {
        @UniqueConstraint(name = "uk-customer-email", columnNames = "email"),
        @UniqueConstraint(name = "uk-customer-phone", columnNames = "phone")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false,length = 200)
    private String email;

    @Column(nullable = false,length = 20)
    private String phone;

    public Customer() {}

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }
}
