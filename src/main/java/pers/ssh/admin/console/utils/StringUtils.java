package pers.ssh.admin.console.utils;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:41 上午
 * Description: The String utility.
 */
public class StringUtils {

    /**
     * Check the input String is null or blank.
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        final int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check the input String is not null and not blank.
     *
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }
}
