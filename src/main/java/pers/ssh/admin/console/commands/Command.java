package pers.ssh.admin.console.commands;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:56 上午
 * Description: The Command interface.
 */
public interface Command {

    void setParameters(String... parameters);

    void execute() throws Exception;

    void preExecute() throws Exception;

    void postExecute() throws Exception;
}
