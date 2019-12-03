package pers.ssh.admin.console.daos.impl;

import pers.ssh.admin.console.daos.GlobalDataPool;
import pers.ssh.admin.console.daos.UserDao;
import pers.ssh.admin.console.entity.User;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:19 下午
 * Description: User data access object.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User findByUserName(final String userName) {
        return GlobalDataPool.findUserByName(userName);
    }

    @Override
    public User create(final User user) throws Exception {
        return GlobalDataPool.createUser(user);
    }
}
