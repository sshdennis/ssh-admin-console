package pers.ssh.admin.console.utils;

import pers.ssh.admin.console.properties.EnvConstants;
import pers.ssh.admin.console.properties.EnvProperty;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 1:36 下午
 * Description:
 */
public class Logger {
    private static final boolean isDebug = EnvProperty.getBoolean(EnvConstants.IS_DEBUG);

    /**
     * Print DEBUG message.
     *
     * @param msg
     */
    public static void debug(final String msg) {
        if (isDebug) {
            System.out.println("[DEBUG] " + msg);
        }
    }

    /**
     * Print Exception messages.
     *
     * @param msg
     * @param t
     */
    public static void error(final String msg, final Throwable t) {
        if (isDebug) {
            System.out.println("[ERROR] " + msg + ". - Error message: " + t.getMessage());
            for (final Object st : t.getStackTrace()) {
                System.out.println("\t" + st);
            }
        }
    }

    /**
     * Print stdout.
     *
     * @param msg
     */
    public static void output(final String msg) {
        System.out.println(msg);
    }
}
