package pers.ssh.admin.console.commands;

import java.util.Arrays;
import java.util.stream.Stream;

import pers.ssh.admin.console.constants.CommandType;
import pers.ssh.admin.console.properties.EnvConstants;
import pers.ssh.admin.console.properties.EnvProperty;
import pers.ssh.admin.console.utils.StringUtils;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:05 上午
 * Description: Generate the Command instance.
 */
public class CommandFactory {

    private static final String BASE_INPUT_COMMAND_PACKAGE = EnvProperty.getString(EnvConstants.BASE_INPUT_COMMAND_PACKAGE);
    private static final String INPUT_COMMAND_PREFIX = EnvProperty.getString(EnvConstants.INPUT_COMMAND_PREFIX);

    /**
     * Create command by given input command line input.
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static Command createCommand(final String input) throws Exception {
        if (StringUtils.isBlank(input)) {
            throw new Exception("Invalid input");
        }
        final String[] ins = cleanInput(input);
        final String cmdName = findCommandName(ins);
        final String[] parameters = findCommandParameters(ins);

        final CommandType cmd = CommandType.valueOf(cmdName);
        final String commandClassPath = BASE_INPUT_COMMAND_PACKAGE + "." + EnvProperty.getString(INPUT_COMMAND_PREFIX + cmd.name());

        // command
        final Command command = (Command) Class.forName(commandClassPath).newInstance();
        command.setParameters(parameters);
        return command;
    }

    private static String[] cleanInput(final String input) {
        String[] ins = input.split(" ");
        ins = Stream.of(ins).filter(StringUtils::isNotBlank).map(String::trim).toArray(String[]::new);
        return ins;
    }

    private static String findCommandName(final String[] ins) {
        return ins[0].toUpperCase();
    }

    private static String[] findCommandParameters(final String[] ins) {
        String[] parameters = null;
        if (ins.length > 1) {
            parameters = Arrays.copyOfRange(ins, 1, ins.length);
        }
        return parameters;
    }
}
