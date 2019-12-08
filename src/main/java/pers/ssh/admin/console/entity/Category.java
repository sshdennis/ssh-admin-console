package pers.ssh.admin.console.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:15 下午
 * Description: Category entity.
 */
public class Category {

    private String name;

    private List<Listing> listings = new ArrayList<>();

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<Listing> getListings() {
        return this.listings;
    }

    public void setListings(final List<Listing> listings) {
        this.listings = listings;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + this.name + '\'' +
                ", listings=" + this.listings +
                '}';
    }
}
