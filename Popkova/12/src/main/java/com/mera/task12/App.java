package com.mera.task12;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class App {
    private final static int ITEM_COUNT = 10;
    private final static int MIN_QUANTITY_BOUND = 15;
    private final static int MAX_QUANTITY_BOUND = 50;
    private final static int MIN_PRICE_BOUND = 20;
    private final static int MAX_PRICE_BOUND = 60;
    private final static int ONE_HELPER = 1;

    public static void main( String[] args ) {
        Shop shop = new Shop();

        addItems(shop);
        shop.showAllSortedItems();
    }

    private static void addItems(Shop shop) {
        Random random = new Random();

        for (int i = 0; i < ITEM_COUNT; i++) {
            String category = RandomStringUtils.randomAlphabetic(10);
            double price = random.nextInt(MAX_PRICE_BOUND - MIN_PRICE_BOUND + ONE_HELPER) + MIN_PRICE_BOUND;
            String name = RandomStringUtils.randomAlphabetic(10);
            int quantity = random.nextInt(MAX_QUANTITY_BOUND - MIN_QUANTITY_BOUND + ONE_HELPER) + MIN_QUANTITY_BOUND;
            String id = RandomStringUtils.randomAlphanumeric(25);

            shop.addItem(new ShopItem(category, price, name, quantity, id));
        }
    }
}