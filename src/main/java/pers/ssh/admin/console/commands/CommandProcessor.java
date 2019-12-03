package pers.ssh.admin.console.commands;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:51 上午
 * Description: Execute the given Command.
 */
public class CommandProcessor {

    public static CommandResponse process(final String input) throws Exception {
        try {
            final Command cmd = CommandFactory.createCommand(input);
            cmd.preExecute();
            final CommandResponse response = cmd.execute();
            cmd.postExecute();

            return response;
        } catch (final Exception e) {
            Logger.error("Process command error. input: " + input, e);
            return CommandResponse.error(e.getMessage());
        }
    }
}
