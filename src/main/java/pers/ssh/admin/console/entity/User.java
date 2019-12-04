package pers.ssh.admin.console.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:14 下午
 * Description: User entity.
 */
public class User extends BaseEntuty {

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
        return "User{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}
