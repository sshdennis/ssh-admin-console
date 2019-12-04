package pers.ssh.admin.console.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    private static final Pattern COMMAND_INPUT_PATTERN = Pattern.compile("\'(.*?)\'");

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
        final List<String> ins = cleanInput(input);
        final String cmdName = findCommandName(ins);
        final List<String> parameters = findCommandParameters(ins);

        final CommandType cmd = CommandType.valueOf(cmdName);
        final String commandClassPath = BASE_INPUT_COMMAND_PACKAGE + "." + EnvProperty.getString(INPUT_COMMAND_PREFIX + cmd.name());

        // command
        final Command command = (Command) Class.forName(commandClassPath).newInstance();
        command.setParameters(parameters);
        return command;
    }

    private static List<String> cleanInput(final String input) {
        final Matcher matcher = COMMAND_INPUT_PATTERN.matcher(input);

        String remain = input;
        final List<String> cmds = new ArrayList<>();
        while (matcher.find()) {
            final String foundStr = matcher.group().trim();
            final String[] parts = remain.split(foundStr);
            final String[] frontParts = parts[0].split(" ");
            cmds.addAll(Arrays.asList(frontParts));

            if (parts.length > 1) {
                remain = parts[1];
            } else {
                // the end
                remain = "";
            }
            cmds.add(foundStr.replaceAll("\'", ""));
        }

        if (StringUtils.isNotBlank(remain)) {
            final String[] lastParts = remain.split(" ");
            cmds.addAll(Arrays.asList(lastParts));
        }

        return cmds.stream().filter(StringUtils::isNotBlank).collect(Collectors.toList());
    }

    private static String findCommandName(final List<String> ins) {
        return ins.get(0).toUpperCase();
    }

    private static List<String> findCommandParameters(final List<String> ins) {
        if (ins.size() > 1) {
            return ins.subList(1, ins.size());
        }
        return new ArrayList<>();
    }
}
