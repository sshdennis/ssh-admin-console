package pers.ssh.admin.console.commands;

import java.util.List;

import pers.ssh.admin.console.daos.UserDao;
import pers.ssh.admin.console.daos.impl.UserDaoImpl;
import pers.ssh.admin.console.entity.User;

/**
 * Author:   dsu01
 * Date:     2019/12/5 11:51 上午
 * Description:
 */
public abstract class InputAuthCommand extends InputCommand {

    private final UserDao userDao = new UserDaoImpl();
    protected User authUser;

    @Override
    public void setParameters(final List<String> parameters) throws Exception {
        super.setParameters(parameters);
        final String userName = parameters.get(0);
        this.authUser = this.userDao.findByUserName(userName);
        if (this.authUser == null) {
            throw new Exception("unknown user");
        }
    }
}
