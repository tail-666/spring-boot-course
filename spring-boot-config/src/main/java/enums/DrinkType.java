package enums;

import lombok.Getter;

@Getter
public enum DrinkType {
    COFFEE("咖啡", 12.0), TEA("奶茶", 10.0), JUICE("果汁", 8.0);
    private final String label;
    private final Double price;

    DrinkType(String label, Double price) {
        this.label = label;
        this.price = price;
    }
}
