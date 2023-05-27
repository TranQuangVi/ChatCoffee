package com.DoAn.ChatCoffee.repository;

import com.DoAn.ChatCoffee.entity.CartDetails;
import com.DoAn.ChatCoffee.entity.CartDetailsKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShoppingCartDetailRepository extends JpaRepository<CartDetails, CartDetailsKey> {
}
