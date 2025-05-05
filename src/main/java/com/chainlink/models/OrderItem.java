package com.chainlink.models;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotNull;

@Entity
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @NotNull
    @OneToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    @NotNull
    private int quantity;

    @NotNull
    private float price;

    @NotNull
    private BigDecimal total;

    @PreUpdate
    @PrePersist
    @SuppressWarnings("unused")
    private void calculateTotal(){
        this.total = BigDecimal.valueOf(price).multiply(BigDecimal.valueOf(quantity));
    }

    public OrderItem() { }

    public OrderItem(UUID id, Order order, Product product, int quantity, float price) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

}
