package pers.ssh.admin.console.commands.impl;

import pers.ssh.admin.console.commands.InputCommand;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:57 上午
 * Description:
 */
public class RegisterCommand extends InputCommand {

    @Override
    public void setParameters(final String... parameters) {
        if (parameters == null || parameters.length == 0) {
            return;
        }

        for (final String parameter : parameters) {
            System.out.println(parameter);
        }
    }

    @Override
    public void execute() throws Exception {
        System.out.println("RegisterCommand");
    }
}
