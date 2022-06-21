package io.ecommerce.DTO;

import java.time.LocalDate;

public class Product {
    private String _productId;
    private String _name;
    private String _description;
    private String _origin;
    private LocalDate _manufactureDate;
    private long _quantity;
    private double _price;
    private long _insuranceDuration;
    private double _discountPercentage;

    public Product() {}

    public Product(
            String productId,
            String name,
            String description,
            String origin,
            LocalDate manufactureDate,
            long quantity,
            double price,
            long insuranceDuration,
            double discountPercentage)
    {
        _productId = productId;
        _name = name;
        _description = description;
        _origin = origin;
        _manufactureDate = manufactureDate;
        _quantity = quantity;
        _price = price;
        _insuranceDuration = insuranceDuration;
        _discountPercentage = discountPercentage;
    }

    public String getProductId() {
        return _productId;
    }

    public void setProductId(String productId) {
        if (!productId.isEmpty()) {
            _productId = productId;
        }
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        if (!name.isEmpty()) {
            _name = name;
        }
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        if (!description.isEmpty()) {
            _description = description;
        }
    }

    public String getOrigin() {
        return _origin;
    }

    public void setOrigin(String origin) {
        if (!origin.isEmpty()) {
            _origin = origin;
        }
    }

    public LocalDate getManufactureDate() {
        return _manufactureDate;
    }

    public void setManufactureDate(LocalDate manufactureDate) {
        _manufactureDate = manufactureDate;
    }

    public Long getQuantity() {
        return _quantity;
    }

    public void setQuantity(long quantity) {
        if (quantity >= 0) {
            _quantity = quantity;
        }
    }

    public Double getPrice() {
        return _price;
    }

    public void setPrice(double price) {
        if (price >= 0.0) {
            _price = price;
        }
    }

    public Long getInsuranceDuration() {
        return _insuranceDuration;
    }

    public void setInsuranceDuration(long insuranceDuration) {
        if (insuranceDuration >= 0) {
            _insuranceDuration = insuranceDuration;
        }
    }

    public Double getDiscountPercentage() {
        return _discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        if (discountPercentage >= 0.0) {
            _discountPercentage = discountPercentage;
        }
    }
}