package pers.ssh.admin.console.commands.impl;

import pers.ssh.admin.console.commands.InputCommand;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:41 上午
 * Description:
 */
public class ExitCommand extends InputCommand {

    @Override
    public void setParameters(final String... parameters) {

    }

    @Override
    public void execute() throws Exception {
        System.out.println("ExitCommand");
        System.exit(0);
    }
}
