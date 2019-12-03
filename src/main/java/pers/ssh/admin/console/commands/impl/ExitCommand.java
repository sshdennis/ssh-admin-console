package pers.ssh.admin.console.commands.impl;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputCommand;
import pers.ssh.admin.console.constants.AdminConsoleConstants;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:41 上午
 * Description:
 */
public class ExitCommand extends InputCommand {

    @Override
    protected void setupParameters(final String[] parameters) {
        // nothing
    }

    @Override
    public CommandResponse execute() throws Exception {
        System.exit(0);
        return CommandResponse.success(AdminConsoleConstants.RES_SUCCESS);
    }
}
