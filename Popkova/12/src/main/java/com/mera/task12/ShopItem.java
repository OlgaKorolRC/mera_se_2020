package com.mera.task12;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;

public class ShopItem {
    String category;
    double price;
    String name;
    int quantity;
    String id;

    public ShopItem(String category, double price, String name, int quantity, String id) {
        this.category = category;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ShopItem shopItem = (ShopItem) o;

        return new EqualsBuilder()
                .append(price, shopItem.price)
                .append(quantity, shopItem.quantity)
                .append(category, shopItem.category)
                .append(name, shopItem.name)
                .append(id, shopItem.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(category)
                .append(price)
                .append(name)
                .append(quantity)
                .append(id)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", StringUtils.abbreviate(id, 10))
                .append("category", StringUtils.abbreviate(category, 10))
                .append("name", StringUtils.abbreviate(name, 10))
                .append("price", price)
                .append("quantity", quantity)
                .toString();
    }
}