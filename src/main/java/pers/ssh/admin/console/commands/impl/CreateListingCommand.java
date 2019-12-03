package pers.ssh.admin.console.commands.impl;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputCommand;
import pers.ssh.admin.console.constants.AdminConsoleConstants;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:59 上午
 * Description:
 */
public class CreateListingCommand extends InputCommand {

    @Override
    protected void setupParameters(final String[] parameters) {

    }

    @Override
    public CommandResponse execute() throws Exception {
        System.out.println("CreateListingCommand");

        return null;
    }
}
