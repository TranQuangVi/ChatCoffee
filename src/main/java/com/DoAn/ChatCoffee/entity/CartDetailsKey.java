package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CartDetailsKey implements Serializable {


        @Column(name = "MaGH")
        Long CartMaGH;

        @Column(name = "id")
        Long ProductId;

        // standard constructors, getters, and setters
        // hashcode and equals implementation
    }
