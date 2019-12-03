package pers.ssh.admin.console.daos;

import pers.ssh.admin.console.entity.User;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:17 下午
 * Description: User data access interface.
 */
public interface UserDao {

    public User findByUserName(String userName);

    public User create(User user) throws Exception;
}
