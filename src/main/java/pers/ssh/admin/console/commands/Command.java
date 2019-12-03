package pers.ssh.admin.console.commands;

import pers.ssh.admin.console.beans.CommandResponse;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:56 上午
 * Description: The Command interface.
 */
public interface Command {

    void setParameters(String... parameters) throws IllegalArgumentException;

    CommandResponse execute() throws Exception;

    void preExecute() throws Exception;

    void postExecute() throws Exception;
}
