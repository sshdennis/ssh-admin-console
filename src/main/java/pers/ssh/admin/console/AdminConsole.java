package pers.ssh.admin.console;

import java.util.Scanner;

import pers.ssh.admin.console.beans.CommandResponse;
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
            System.out.print("# ");
            try {
                in = reader.nextLine();
                final CommandResponse response = CommandProcessor.process(in);
                handleResponse(response);
            } catch (final Exception e) {
                Logger.error("Input error: " + in, e);
                printHelp();
            }
        }
    }

    private static void handleResponse(final CommandResponse commandResponse) {
        if (commandResponse == null) {
            return;
        }

        if (commandResponse.isSuccess()) {
            System.out.println(commandResponse.getResponse());
        } else {
            System.out.println("Error - " + commandResponse.getResponse());
        }
    }

    private static void printHelp() {
        // TODO
    }
}
