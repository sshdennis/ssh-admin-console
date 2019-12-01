package pers.ssh.admin.console.commands;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:51 上午
 * Description: Execute the given Command.
 */
public class CommandProcessor {

    public static void process(final String input) throws Exception {
        final Command cmd = CommandFactory.createCommand(input);
        cmd.preExecute();
        cmd.execute();
        cmd.postExecute();
    }
}
