package pers.ssh.admin.console.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 10:19 上午
 * Description:
 */
public class StringUtilsTest {

    @Test
    public void testIsBlank() {
        Assert.assertTrue(StringUtils.isBlank(null));
        Assert.assertTrue(StringUtils.isBlank(""));
        Assert.assertTrue(StringUtils.isBlank(" "));

        Assert.assertFalse(StringUtils.isBlank("abc"));
        Assert.assertFalse(StringUtils.isBlank(" aa"));
        Assert.assertFalse(StringUtils.isBlank("de "));
        Assert.assertFalse(StringUtils.isBlank("&%@@"));
    }

    @Test
    public void testIsNotBlank() {
        Assert.assertFalse(StringUtils.isNotBlank(null));
        Assert.assertFalse(StringUtils.isNotBlank(""));
        Assert.assertFalse(StringUtils.isNotBlank(" "));

        Assert.assertTrue(StringUtils.isNotBlank("abc"));
        Assert.assertTrue(StringUtils.isNotBlank(" aa"));
        Assert.assertTrue(StringUtils.isNotBlank("de "));
        Assert.assertTrue(StringUtils.isNotBlank("&%@@"));
    }
}
