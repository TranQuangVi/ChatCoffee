package com.DoAn.ChatCoffee.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "CartDetails")
public class CartDetails {

    @EmbeddedId
    CartDetailsKey id;


    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    @ManyToOne
    @MapsId("cart_id")
    @JoinColumn(name = "cart_id")
    Cart cart;

    @Column(name = "SoLuong")
    private int SoLuong;

/*    @Column(name = "")
    private int TongSL;

    @Column(name = "TongSP")
    private int TongSP;*/

    public CartDetailsKey getId() {
        return id;
    }

    public void setId(CartDetailsKey id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }
}
