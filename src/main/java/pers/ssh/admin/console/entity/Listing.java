package pers.ssh.admin.console.entity;

import java.util.Date;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:15 下午
 * Description: Listing entity.
 */
public class Listing extends BaseEntuty {

    private String userName;
    private Date createdAt;

    private String title;
    private String description;
    private long price;
    private String category;

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(final long price) {
        this.price = price;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Listing{" +
                "userName='" + this.userName + '\'' +
                ", createdAt=" + this.createdAt +
                ", title='" + this.title + '\'' +
                ", description='" + this.description + '\'' +
                ", price=" + this.price +
                ", category='" + this.category + '\'' +
                '}';
    }
}
