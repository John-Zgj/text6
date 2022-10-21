package cn.edu.jnu.text6.data;

import java.io.Serializable;

public class ShopItem  implements Serializable {
    public ShopItem(String title, double price, int resourceId) {
        this.title = title;
        this.price = price;
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    private String title;
    private double price;
    private int resourceId;
}
