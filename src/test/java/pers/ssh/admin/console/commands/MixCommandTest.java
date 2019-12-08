package pers.ssh.admin.console.commands;

import org.junit.Assert;
import org.junit.Test;
import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.daos.GlobalDataPool;

/**
 * Author:   Dennis Si
 * Date:     2019/12/8 8:12 下午
 * Description:
 */
public class MixCommandTest {

    @Test
    public void testExecute() throws Exception {
        CommandResponse resp =CommandProcessor.process("REGISTER user1");
        Assert.assertEquals(resp.getResponse(), "Success");

        resp = CommandProcessor.process("CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'");
        Thread.sleep(2);
        Assert.assertEquals(resp.getResponse(), "100001");

        resp = CommandProcessor.process("GET_LISTING user1 100001");
        Assert.assertTrue(resp.getResponse().startsWith("Phone model 8|Black color, brand new|1000|"));
        Assert.assertTrue(resp.getResponse().endsWith("|Electronics|user1"));

        resp = CommandProcessor.process("CREATE_LISTING user1 'Black shoes' 'Training shoes' 100 'Sports'");
        Thread.sleep(2);
        Assert.assertEquals(resp.getResponse(), "100002");

        resp =CommandProcessor.process("REGISTER user2");
        Assert.assertEquals(resp.getResponse(), "Success");

        resp =CommandProcessor.process("REGISTER user2");
        Assert.assertEquals(resp.getResponse(), "user already existing");

        resp = CommandProcessor.process("CREATE_LISTING user2 'T-shirt' 'White color' 20 'Sports'");
        Thread.sleep(2);
        Assert.assertEquals(resp.getResponse(), "100003");

        resp = CommandProcessor.process("GET_LISTING user1 100003");
        Assert.assertTrue(resp.getResponse().startsWith("T-shirt|White color|20|"));
        Assert.assertTrue(resp.getResponse().endsWith("|Sports|user2"));

        resp = CommandProcessor.process("GET_CATEGORY user1 'Fashion' sort_time asc");
        Assert.assertEquals(resp.getResponse(), "category not found");

        resp = CommandProcessor.process("GET_CATEGORY user1 'Sports' sort_time dsc");
        String[] list = resp.getResponse().split("\n");
        Assert.assertTrue(list[0].startsWith("T-shirt|White color|20|"));
        Assert.assertTrue(list[1].startsWith("Black shoes|Training shoes|100|"));

        resp = CommandProcessor.process("GET_CATEGORY user1 'Sports' sort_price dsc");
        list = resp.getResponse().split("\n");
        Assert.assertTrue(list[0].startsWith("Black shoes|Training shoes|100|"));
        Assert.assertTrue(list[1].startsWith("T-shirt|White color|20|"));

        resp = CommandProcessor.process("GET_TOP_CATEGORY user1");
        Assert.assertEquals(resp.getResponse(), "Sports");

        resp = CommandProcessor.process("DELETE_LISTING user1 100003");
        Assert.assertEquals(resp.getResponse(), "listing owner mismatch");

        resp = CommandProcessor.process("DELETE_LISTING user2 100003");
        Assert.assertEquals(resp.getResponse(), "Success");

        resp = CommandProcessor.process("GET_TOP_CATEGORY user2");
        Assert.assertEquals(resp.getResponse(), "Electronics");

        resp = CommandProcessor.process("DELETE_LISTING user1 100002");
        Assert.assertEquals(resp.getResponse(), "Success");

        resp = CommandProcessor.process("GET_TOP_CATEGORY user1");
        Assert.assertEquals(resp.getResponse(), "Electronics");

        resp = CommandProcessor.process("GET_TOP_CATEGORY user3");
        Assert.assertEquals(resp.getResponse(), "unknown user");
    }
}
