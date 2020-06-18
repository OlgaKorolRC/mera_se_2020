package task1;

import java.util.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;



public class Task1 {
    public static void main( String[] args ) {
        Shop shop = new Shop();

        shop.addItem("продукты", 70., "яблоки", 80);
        shop.addItem("канцтовары", 100., "ножницы", 40);
        shop.addItem("продукты", 20., "батон", 50);
        shop.addItem("продукты", 50., "кетчуп", 30);
        shop.addItem("канцтовары", 200., "краски", 35);
        shop.addItem("канцтовары", 15., "тетрадь", 100);
        shop.addItem("бытовые", 45., "стиральный порошок", 40);
        shop.addItem("бытовые", 250., "швабра", 20);
        shop.addItem("бытовые", 300., "тапочки", 15);
        shop.addItem("продукты", 300., "черешня", 100);

        System.out.println("ID            Категория   Наименование    Цена    Остаток");


        for (int i = 0; i < shop.items.size(); i++) {
            System.out.println(StringUtils.abbreviate(shop.items.get(i).ID, 13) + " " + StringUtils.abbreviate(shop.items.get(i).cathegory, 13) + " " + StringUtils.abbreviate(shop.items.get(i).denomination, 13) + " " + shop.items.get(i).price + " " + shop.items.get(i).number);

        }



        shop.comparatorList.add(ItemSorters::sortByCategory);
        shop.comparatorList.add(ItemSorters::sortByTitle);
        shop.comparatorList.add(ItemSorters::sortByPrice);
        shop.comparatorList.add(ItemSorters::sortByQuantity);

        Random rnd = new Random();
        int sortBy = rnd.nextInt(4);
        System.out.println("");


        switch (sortBy) {
            case 0:
                System.out.println("sortByCategory");
                break;
            case 1:
                System.out.println("sortByTitle");
                break;
            case 2:
                System.out.println("sortByPrice");
                break;
            case 3:
                System.out.println("sortByQuantity");
                break;
            default:
                sortBy = 0;
                break;
        }

        Collections.sort(shop.items, shop.comparatorList.get(sortBy));


        System.out.println("\nID            Категория   Наименование    Цена    Остаток");


        for (int i = 0; i < shop.items.size(); i++) {
            System.out.println(StringUtils.abbreviate(shop.items.get(i).ID, 13) + " " + StringUtils.abbreviate(shop.items.get(i).cathegory, 13) + " " + StringUtils.abbreviate(shop.items.get(i).denomination, 13) + " " + shop.items.get(i).price + " " + shop.items.get(i).number);

        }

    }
}

class ShopItem {

    String cathegory;
    double price;
    String denomination;
    int number;
    String ID;


    ShopItem(String cathegory, double price, String denomination, int number, String ID) {
        this.cathegory = cathegory; // Категория
        this.price = price; // Цена
        this.denomination = denomination; // Наименование
        this.number = number; // Количество
        this.ID = ID; // ID
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof ShopItem)) {
            return false;
        }

        ShopItem item = (ShopItem) o;

        return new EqualsBuilder()
            .append(cathegory, item.cathegory)
            .append(price, item.price)
            .append(denomination, item.denomination)
            .append(number, item.number)
            .append(ID, item.ID)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(cathegory)
            .append(price)
            .append(denomination)
            .append(number)
            .append(ID)
            .toHashCode();
    }


}

class Shop {
    List < Comparator < ShopItem >> comparatorList = new ArrayList < Comparator < ShopItem >> ();


    ArrayList < ShopItem > items = new ArrayList < > ();

    void addItem(String cathegory, double price, String denomination, int number) {
        items.add(new ShopItem(cathegory, price, denomination, number, RandomStringUtils.randomAlphanumeric(25)));

    }
}

// способы сортировки
class ItemSorters {

    static int sortByCategory(ShopItem o1, ShopItem o2) {
        return o1.cathegory.compareTo(o2.cathegory);
    }


    static int sortByTitle(ShopItem o1, ShopItem o2) {
        return o1.denomination.compareTo(o2.denomination);
    }



    static int sortByPrice(ShopItem o1, ShopItem o2) {
        return (o1.price > o2.price ? 1 : (o1.price == o2.price) ? 0 : -1);
    }

    static int sortByQuantity(ShopItem o1, ShopItem o2) {
        return (o1.number > o2.number ? 1 : (o1.number == o2.number) ? 0 : -1);
    }
}
