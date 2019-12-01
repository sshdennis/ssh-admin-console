package pers.ssh.admin.console.properties;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:13 上午
 * Description:
 */
public class EnvProperty {
    private static final String DEFAULT_BUNDLE_NAME = "config.properties";
    private static final ConfigProperty bundleProperty = new ConfigProperty(DEFAULT_BUNDLE_NAME);

    /**
     * Get string config property from property file.
     *
     * @param key
     * @return
     */
    public static String getString(final String key) {
        return bundleProperty.getString(key);
    }

    /**
     * Get integer config property from property file.
     *
     * @param key
     * @return
     */
    public static int getInt(final String key) {
        return bundleProperty.getInt(key);
    }

    /**
     * Get boolean config property from property file.
     *
     * @param key
     * @return
     */
    public static boolean getBoolean(final String key) {
        return bundleProperty.getBoolean(key);
    }
}
