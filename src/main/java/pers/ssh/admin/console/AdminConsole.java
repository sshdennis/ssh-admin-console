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
            Logger.output(commandResponse.getResponse());
        } else {
            Logger.output("Error - " + commandResponse.getResponse());
        }
    }

    private static void printHelp() {
        System.out.println("Illegal argument...");
        System.out.println("Usage -");
        System.out.println("\tREGISTER <username>");
        System.out.println("\tCREATE_LISTING <username> <title> <description> <price> <category>");
        System.out.println("\tDELETE_LISTING <username> <listing_id>");
        System.out.println("\tGET_LISTING <username> <listing_id>");
        System.out.println("\tGET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}");
        System.out.println("\tGET_TOP_CATEGORY <username>");
        System.out.println("\tEXIST");
    }
}
