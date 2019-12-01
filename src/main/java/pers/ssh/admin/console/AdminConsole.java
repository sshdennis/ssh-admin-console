package pers.ssh.admin.console;

import java.util.Scanner;

import pers.ssh.admin.console.commands.CommandProcessor;
import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 9:54 上午
 * Description: The entry point for this admin console.
 */
public class AdminConsole {

    public static void main(final String[] args) {
        Logger.debug("Starting...");

        final Scanner reader = new Scanner(System.in);
        final boolean done = false;
        String in = null;
        while (!done) {
            System.out.print("> ");
            try {
                in = reader.nextLine();
                CommandProcessor.process(in);

            } catch (final Exception e) {
                Logger.error("Input error: " + in, e);
                printHelp();
            }
        }
    }

    private static void printHelp() {
        // TODO
    }
}
