package com.mera.gokhmak.les12;

public class ItemSorters {
    public static int sortByCategory(ShopItem item1, ShopItem item2) {
        return item1.getCategory().compareTo(item2.getCategory());
    }
    public static int sortByTitle(ShopItem item1, ShopItem item2) {
        return item1.getName().compareTo(item2.getName());
    }
    public static int sortByPrice(ShopItem item1, ShopItem item2) {
        return new Double(item1.getPrice()).compareTo(item2.getPrice());
    }
    public static int sortByQuantity(ShopItem item1, ShopItem item2) {
        return new Integer(item1.getAmount()).compareTo(item2.getAmount());
    }
}
