package com.mera.gokhmak.les12;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Shop {
    private List<ShopItem> items = new ArrayList<>();
    private List<Comparator<ShopItem>> comparators = new ArrayList<>();
    private final int QUANTITY = 10;
    static Map<String,String[]> products = new HashMap<>();
    static {
        products.put("Т.д/я похода", new String[]{"Лодка","Удочка","Сапоги","Ружье","Компас","Котелок","Патроны"});
        products.put("Т.д/я дома", new String[]{"Мыло","Веревка","Гравицапа","Смеситель","Ведро","Швабра"});
        products.put("Т.д/я огорода", new String[]{"Лопата","Лейка","Огурцы (семена)", "Шланг для полива"});
    }
    static Tuple2<String,String> getRandomProduct() {
        String[] categories = products.keySet().toArray(new String[products.keySet().size()]);
        String category = categories[(int)(Math.random() * categories.length)];
        return new Tuple2<>(category, products.get(category)[(int)(Math.random() * products.get(category).length)]);
    }

    {
        for (int i = 0; i < QUANTITY; i++) {
            Tuple2<String,String> item = getRandomProduct();
            items.add(new ShopItem(
                    RandomStringUtils.randomAlphanumeric(25),
                    item.first,
                    item.second,
                    (Math.random() * 1000) + 10,
                    (int) (Math.random() * 50) + 1
            ));
        }
        comparators.add((o1, o2) -> ItemSorters.sortByCategory(o1,o2));
        comparators.add((o1, o2) -> ItemSorters.sortByTitle(o1,o2));
        comparators.add((o1, o2) -> ItemSorters.sortByPrice(o1,o2));
        comparators.add((o1, o2) -> ItemSorters.sortByQuantity(o1,o2));
    }

    public void printAll() {
        int sorter = (int)(Math.random() * comparators.size());
        System.out.println("Сортировка по колонке: " + (sorter + 2));
        System.out.printf("%-10s %-15s %-15s %10s %10s\n", "ID", "Категория", "Наименование", "Цена", "Остаток");
        System.out.printf("%-10s %15s %15s %10s %10s\n", "--", "---------------", "---------------", "---------", "-------");
        Collections.sort(items, comparators.get(sorter));
        for (ShopItem item : items) {
            System.out.printf("%10s %15s %15s %9.2f$ %10d\n",
                    StringUtils.abbreviate(item.getID(), 10),
                    StringUtils.abbreviate(item.getCategory(), 15),
                    StringUtils.abbreviate(item.getName(), 15),
                    item.getPrice(),
                    item.getAmount()
            );
        }
    }
}

class Tuple2<A,B> {
    public final A first;
    public final B second;
    public Tuple2(A a, B b) {
        this.first = a;
        this.second = b;
    }
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}