package pers.ssh.admin.console.commands;

import org.junit.Assert;
import org.junit.Test;
import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.constants.ErrorMessage;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 11:14 上午
 * Description:
 */
public class ListingCommandTest extends BaseCommand {

    @Test
    public void testCreateListing() {
        try {
            CommandResponse resp = this.createListing(MOCK_USER_NAME_A, "mockTitle01", "mockDesc01", "1000", "mockCategory");
            Assert.assertTrue(resp.isSuccess());
            Assert.assertEquals(resp.getResponse(), "100001");

            resp = this.createListing(MOCK_USER_NAME_A, "mockTitle02", "mockDesc02", "99", "mockCategory");
            Assert.assertTrue(resp.isSuccess());
            Assert.assertEquals(resp.getResponse(), "100002");

            resp = this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "10000", "mockCategory");
            Assert.assertTrue(resp.isSuccess());
            Assert.assertEquals(resp.getResponse(), "100003");

            resp = this.getListing(MOCK_USER_NAME_A, "100001");
            Assert.assertTrue(resp.getResponse().contains("mockTitle01|mockDesc01|1000|"));
            Assert.assertTrue(resp.getResponse().contains("|mockCategory|mockuser001"));

            resp = this.getListing(MOCK_USER_NAME_A, "100003");
            Assert.assertTrue(resp.getResponse().contains("mockTitle03|mockDesc03|10000|"));
            Assert.assertTrue(resp.getResponse().contains("|mockCategory|mockuser002"));
        } catch (final Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testGetListingNotFound() {
        try {
            this.getListing(MOCK_USER_NAME_A, "99");

            Assert.fail();
        } catch (final Exception e) {
            e.printStackTrace();

            Assert.assertEquals(e.getMessage(), ErrorMessage.RESOURCE_NOT_FOUND);
        }
    }

    @Test
    public void testDeleteListing() {
        try {
            CommandResponse resp = this.createListing(MOCK_USER_NAME_A, "mockTitle01", "mockDesc01", "1000", "mockCategory");
            final String listingId = resp.getResponse();
            resp = this.deleteListing(MOCK_USER_NAME_A, listingId);

            Assert.assertTrue(resp.isSuccess());
            Assert.assertEquals(resp.getResponse(), CommandResponse.RES_SUCCESS);

            this.getListing(MOCK_USER_NAME_A, listingId);
            Assert.fail();
        } catch (final Exception e) {
            e.printStackTrace();

            Assert.assertEquals(e.getMessage(), ErrorMessage.RESOURCE_NOT_FOUND);
        }
    }

    @Test
    public void testDeleteListingOwnerMismatch() {
        try {
            final CommandResponse resp = this.createListing(MOCK_USER_NAME_B, "mockTitle01", "mockDesc01", "1000", "mockCategory");
            final String listingId = resp.getResponse();
            this.deleteListing(MOCK_USER_NAME_A, listingId);
            Assert.fail();
        } catch (final Exception e) {
            e.printStackTrace();

            Assert.assertEquals(e.getMessage(), ErrorMessage.LISTING_OWNER_MISMATCH);
        }
    }
}
