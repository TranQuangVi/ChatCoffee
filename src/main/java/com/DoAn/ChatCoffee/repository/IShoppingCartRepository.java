package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartRepository extends JpaRepository<Cart, Long> {
}
