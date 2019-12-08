package pers.ssh.admin.console.commands;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.impl.CreateListingCommand;
import pers.ssh.admin.console.commands.impl.DeleteListingCommand;
import pers.ssh.admin.console.commands.impl.GetCategoryCommand;
import pers.ssh.admin.console.commands.impl.GetListingCommand;
import pers.ssh.admin.console.commands.impl.GetTopCategoryCommand;
import pers.ssh.admin.console.commands.impl.RegisterCommand;
import pers.ssh.admin.console.daos.GlobalDataPool;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 11:40 上午
 * Description:
 */
public class BaseCommand {

    protected final RegisterCommand registerCmd = new RegisterCommand();

    protected final CreateListingCommand createListingCmd = new CreateListingCommand();
    protected final GetListingCommand getListingCmd = new GetListingCommand();
    protected final DeleteListingCommand deleteListingcmd = new DeleteListingCommand();

    protected final GetCategoryCommand getCategoryCommand = new GetCategoryCommand();
    protected final GetTopCategoryCommand getTopCategoryCommand = new GetTopCategoryCommand();

    protected static final String MOCK_USER_NAME_A = "mockuser001";
    protected static final String MOCK_USER_NAME_B = "mockuser002";

    @BeforeClass
    public static void setup() throws Exception {
        final BaseCommand cmd = new BaseCommand();
        cmd.createUser("mockUser001");
        cmd.createUser("MOCKuser002");
    }

    @AfterClass
    public static void teardown() {
        GlobalDataPool.reset();
    }

    protected CommandResponse createUser(final String userName) throws Exception {
        this.registerCmd.setParameters(Arrays.asList(userName));
        final CommandResponse resp = this.registerCmd.execute();

        Assert.assertTrue(resp.isSuccess());
        Assert.assertEquals(resp.getResponse(), CommandResponse.RES_SUCCESS);
        return resp;
    }

    protected CommandResponse createListing(final String userName, final String title, final String desc, final String price, final String category) throws Exception {
        this.createListingCmd.setParameters(Arrays.asList(userName, title, desc, price, category));
        return this.createListingCmd.execute();
    }

    protected CommandResponse getListing(final String userName, final String listingId) throws Exception {
        this.getListingCmd.setParameters(Arrays.asList(userName, listingId));
        return this.getListingCmd.execute();
    }

    protected CommandResponse deleteListing(final String userName, final String listingId) throws Exception {
        this.deleteListingcmd.setParameters(Arrays.asList(userName, listingId));
        return this.deleteListingcmd.execute();
    }

    protected CommandResponse getCategory(final String userName, final String category, final String sortBy, final String orderBy) throws Exception {
        this.getCategoryCommand.setParameters(Arrays.asList(userName, category, sortBy, orderBy));
        return this.getCategoryCommand.execute();
    }

    protected CommandResponse getTopCategoryCommand(final String userName) throws Exception {
        this.getTopCategoryCommand.setParameters(Arrays.asList(userName));
        return this.getTopCategoryCommand.execute();
    }
}
