package pers.ssh.admin.console.commands.impl;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputCommand;
import pers.ssh.admin.console.constants.AdminConsoleConstants;
import pers.ssh.admin.console.daos.UserDao;
import pers.ssh.admin.console.daos.impl.UserDaoImpl;
import pers.ssh.admin.console.entity.User;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:57 上午
 * Description:
 */
public class RegisterCommand extends InputCommand {

    private final UserDao userDao = new UserDaoImpl();
    private String userName;

    @Override
    protected void setupParameters(final String[] parameters) {
        this.userName = parameters[0];
    }

    @Override
    public CommandResponse execute() throws Exception {
        final User user = new User();
        user.setName(this.userName);
        this.userDao.create(user);

        return CommandResponse.success(AdminConsoleConstants.RES_SUCCESS);
    }
}
