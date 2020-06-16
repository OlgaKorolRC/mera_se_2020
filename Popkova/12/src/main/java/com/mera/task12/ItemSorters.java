package com.mera.task12;

public class ItemSorters {

    public int sortByCategory(ShopItem firstItem, ShopItem secondItem) {
        return firstItem.category.compareTo(secondItem.category);
    }

    public int sortByName(ShopItem firstItem, ShopItem secondItem) {
        return firstItem.name.compareTo(secondItem.name);
    }

    public int sortByPrice(ShopItem firstItem, ShopItem secondItem) {
        return (int) (firstItem.price - secondItem.price);
    }

    public int sortByQuantity(ShopItem firstItem, ShopItem secondItem) {
        return firstItem.quantity - secondItem.quantity;
    }

    public int sortById(ShopItem firstItem, ShopItem secondItem) {
        return firstItem.id.compareTo(secondItem.id);
    }
}