package pers.ssh.admin.console.entity;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:14 下午
 * Description: User entity.
 */
public class User extends BaseEntuty {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.name + '\'' +
                '}';
    }
}
