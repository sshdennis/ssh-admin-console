package pers.ssh.admin.console.commands;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.constants.ErrorMessage;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 11:20 上午
 * Description:
 */
public class RegisterCommandTest extends BaseCommand {

    @Test
    public void testExecute() {
        try {
            this.registerCmd.setParameters(Arrays.asList("mockUserName"));
            final CommandResponse resp = this.registerCmd.execute();

            Assert.assertTrue(resp.isSuccess());
            Assert.assertEquals(resp.getResponse(), CommandResponse.RES_SUCCESS);
        } catch (final Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteIgnoreCase() {
        try {
            this.createUser("mockUser001");
            this.createUser("mockuser001");
            Assert.fail();
        } catch (final Exception e) {
            e.printStackTrace();

            Assert.assertEquals(e.getMessage(), ErrorMessage.USER_EXISTING);
        }
    }
}
