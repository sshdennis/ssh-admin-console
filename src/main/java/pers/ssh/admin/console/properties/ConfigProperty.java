package pers.ssh.admin.console.properties;

import java.io.InputStream;
import java.util.Properties;

import pers.ssh.admin.console.utils.Logger;
import pers.ssh.admin.console.utils.StringUtils;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 11:15 上午
 * Description:
 */
public class ConfigProperty {

    private Properties properties;

    public ConfigProperty(final String inputBundle) {
        try (final InputStream input = ConfigProperty.class.getClassLoader().getResourceAsStream(inputBundle)) {
            this.properties = new Properties();
            this.properties.load(input);
        } catch (final Exception e) {
            Logger.error("Load inputBundle error, inputBundle: " + inputBundle, e);
        }
    }

    public String getString(final String key) {
        final String value = this.getMessage(key);
        return value;
    }

    public int getInt(final String key) {
        int value = 0;
        final String numStr = this.getString(key);
        try {
            if (!StringUtils.isBlank(numStr)) {
                value = Integer.valueOf(numStr);
            }
        } catch (final NumberFormatException e) {
            Logger.error("Convert property to Integer error, key: " + key, e);
        }
        return value;
    }

    public boolean getBoolean(final String key) {
        return "true".equalsIgnoreCase(this.getString(key));
    }

    private String getMessage(final String code) throws IllegalArgumentException {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        final String msg = this.properties.getProperty(code);
        if (msg != null) {
            return msg.trim();
        } else {
            return null;
        }
    }
}
