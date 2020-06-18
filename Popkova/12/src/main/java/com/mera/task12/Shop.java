package com.mera.task12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Shop {
    private ArrayList<ShopItem> shopItemList = new ArrayList<>();
    private List<Comparator<ShopItem>> comparators = new ArrayList<>();

    public Shop() {
        ItemSorters sorters = new ItemSorters();

        comparators.add(sorters::sortByCategory);
        comparators.add(sorters::sortByPrice);
        comparators.add(sorters::sortByName);
        comparators.add(sorters::sortByQuantity);
        comparators.add(sorters::sortById);
    }

    public void addItem(ShopItem item) {
        shopItemList.add(item);
    }

    public void showAllSortedItems() {
        int comparatorIndex = new Random().nextInt(comparators.size());
        System.out.println("Comparator index: " + comparatorIndex);

        shopItemList.sort(comparators.get(comparatorIndex));
        shopItemList.forEach(item -> System.out.println(item.toString()));
    }
}