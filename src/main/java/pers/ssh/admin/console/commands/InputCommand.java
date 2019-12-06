package pers.ssh.admin.console.commands;

import java.util.List;

import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:01 上午
 * Description: Handle the stand input command.
 */
public abstract class InputCommand implements Command {

    @Override
    public void setParameters(final List<String> parameters) throws Exception {
        for (final String parameter : parameters) {
            Logger.debug("Input parameter: " + parameter);
        }

        this.setupParameters(parameters);
    }

    @Override
    public void preExecute() throws Exception {
        Logger.debug("Execute command: " + this.getClass().getSimpleName());
    }

    @Override
    public void postExecute() throws Exception {
        // nothing
    }

    protected abstract void setupParameters(List<String> parameters);
}
