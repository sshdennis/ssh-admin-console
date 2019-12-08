package pers.ssh.admin.console.commands;

import org.junit.Assert;
import org.junit.Test;
import pers.ssh.admin.console.beans.CommandResponse;

/**
 * Author:   Dennis Su
 * Date:     2019/12/8 7:27 下午
 * Description:
 */
public class CategoryCommandTest extends BaseCommand {

    @Test
    public void testGetCategory() throws Exception {
        this.createListing(MOCK_USER_NAME_A, "mockTitle01", "mockDesc01", "1000", "mockCategory");
        Thread.sleep(2);
        this.createListing(MOCK_USER_NAME_A, "mockTitle02", "mockDesc02", "99", "mockCategory1");
        Thread.sleep(2);
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "9", "mockCategory");
        Thread.sleep(2);
        this.createListing(MOCK_USER_NAME_A, "mockTitle04", "mockDesc04", "5566", "mockCategory1");
        Thread.sleep(2);
        this.createListing(MOCK_USER_NAME_B, "mockTitle05", "mockDesc05", "10001", "mockCategory1");

        final CommandResponse resp = this.getCategory(MOCK_USER_NAME_B, "mockCategory", null, null);
        final String[] list = resp.getResponse().split("\n");
        Assert.assertEquals(list.length, 2);
        Assert.assertTrue(list[0].startsWith("mockTitle01|mockDesc01|1000|"));
        Assert.assertTrue(list[1].startsWith("mockTitle03|mockDesc03|9|"));

        CommandResponse sortResp = this.getCategory(MOCK_USER_NAME_B, "mockCategory1", "sort_price", "asc");
        String[] sortList = sortResp.getResponse().split("\n");
        Assert.assertEquals(sortList.length, 3);
        Assert.assertTrue(sortList[0].startsWith("mockTitle02|mockDesc02|99|"));
        Assert.assertTrue(sortList[1].startsWith("mockTitle04|mockDesc04|5566|"));
        Assert.assertTrue(sortList[2].startsWith("mockTitle05|mockDesc05|10001|"));

        sortResp = this.getCategory(MOCK_USER_NAME_B, "mockCategory1", "sort_price", "dsc");
        sortList = sortResp.getResponse().split("\n");
        Assert.assertEquals(sortList.length, 3);
        Assert.assertTrue(sortList[0].startsWith("mockTitle05|mockDesc05|10001|"));
        Assert.assertTrue(sortList[1].startsWith("mockTitle04|mockDesc04|5566|"));
        Assert.assertTrue(sortList[2].startsWith("mockTitle02|mockDesc02|99|"));

        sortResp = this.getCategory(MOCK_USER_NAME_B, "mockCategory1", "sort_time", "asc");
        sortList = sortResp.getResponse().split("\n");
        Assert.assertEquals(sortList.length, 3);
        Assert.assertTrue(sortList[0].startsWith("mockTitle02|mockDesc02|99|"));
        Assert.assertTrue(sortList[1].startsWith("mockTitle04|mockDesc04|5566|"));
        Assert.assertTrue(sortList[2].startsWith("mockTitle05|mockDesc05|10001|"));

        sortResp = this.getCategory(MOCK_USER_NAME_B, "mockCategory1", "sort_time", "dsc");
        sortList = sortResp.getResponse().split("\n");
        Assert.assertEquals(sortList.length, 3);
        Assert.assertTrue(sortList[0].startsWith("mockTitle05|mockDesc05|10001|"));
        Assert.assertTrue(sortList[1].startsWith("mockTitle04|mockDesc04|5566|"));
        Assert.assertTrue(sortList[2].startsWith("mockTitle02|mockDesc02|99|"));
    }

    @Test
    public void testGetCategoryWithDeleteListing() throws Exception {
        final CommandResponse createListingResp = this.createListing(MOCK_USER_NAME_A, "mockTitle01", "mockDesc01", "1000", "mockCategoryA");
        this.createListing(MOCK_USER_NAME_A, "mockTitle02", "mockDesc02", "99", "mockCategoryA");
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "10000", "mockCategoryB");
        this.createListing(MOCK_USER_NAME_A, "mockTitle04", "mockDesc04", "9527", "mockCategoryA");

        final CommandResponse beforeDeleteResp = this.getCategory(MOCK_USER_NAME_B, "mockCategoryA", null, null);
        final String[] beforeDeleteList = beforeDeleteResp.getResponse().split("\n");
        Assert.assertEquals(beforeDeleteList.length, 3);
        Assert.assertTrue(beforeDeleteList[0].startsWith("mockTitle01|mockDesc01|1000|"));
        Assert.assertTrue(beforeDeleteList[1].startsWith("mockTitle02|mockDesc02|99|"));
        Assert.assertTrue(beforeDeleteList[2].startsWith("mockTitle04|mockDesc04|9527|"));

        this.deleteListing(MOCK_USER_NAME_A, createListingResp.getResponse());

        final CommandResponse afterDeleteResp = this.getCategory(MOCK_USER_NAME_B, "mockCategoryA", null, null);
        final String[] afterDeleteList = afterDeleteResp.getResponse().split("\n");
        Assert.assertEquals(afterDeleteList.length, 2);
        Assert.assertTrue(afterDeleteList[0].startsWith("mockTitle02|mockDesc02|99|"));
        Assert.assertTrue(afterDeleteList[1].startsWith("mockTitle04|mockDesc04|9527|"));
    }

    @Test
    public void testGetTopCategory() throws Exception {
        this.createListing(MOCK_USER_NAME_A, "mockTitle01", "mockDesc01", "1000", "mockCategoryAA");
        this.createListing(MOCK_USER_NAME_A, "mockTitle02", "mockDesc02", "99", "mockCategoryCC");
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "5566", "mockCategoryAA");
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "5566", "mockCategoryAA");
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "5566", "mockCategoryAA");
        this.createListing(MOCK_USER_NAME_B, "mockTitle03", "mockDesc03", "5566", "mockCategoryAA");
        final CommandResponse createListingRespA = this.createListing(MOCK_USER_NAME_A, "mockTitle04", "mockDesc04", "168", "mockCategoryBB");
        final CommandResponse createListingRespB = this.createListing(MOCK_USER_NAME_B, "mockTitle05", "mockDesc05", "1000", "mockCategoryBB");
        final CommandResponse createListingRespC = this.createListing(MOCK_USER_NAME_B, "mockTitle06", "mockDesc06", "9527", "mockCategoryBB");
        final CommandResponse createListingRespD = this.createListing(MOCK_USER_NAME_B, "mockTitle06", "mockDesc06", "9527", "mockCategoryBB");
        final CommandResponse createListingRespE = this.createListing(MOCK_USER_NAME_B, "mockTitle06", "mockDesc06", "9527", "mockCategoryBB");
        final CommandResponse createListingRespF = this.createListing(MOCK_USER_NAME_B, "mockTitle06", "mockDesc06", "9527", "mockCategoryBB");

        final CommandResponse resp = this.getTopCategoryCommand(MOCK_USER_NAME_A);
        Assert.assertEquals(resp.getResponse(), "mockCategoryBB");

        this.deleteListing(MOCK_USER_NAME_A, createListingRespA.getResponse());
        this.deleteListing(MOCK_USER_NAME_B, createListingRespB.getResponse());
        this.deleteListing(MOCK_USER_NAME_B, createListingRespC.getResponse());
        this.deleteListing(MOCK_USER_NAME_B, createListingRespD.getResponse());
        this.deleteListing(MOCK_USER_NAME_B, createListingRespE.getResponse());
        this.deleteListing(MOCK_USER_NAME_B, createListingRespF.getResponse());

        final CommandResponse afterDeleteResp = this.getTopCategoryCommand(MOCK_USER_NAME_A);
        Assert.assertEquals(afterDeleteResp.getResponse(), "mockCategoryAA");
    }
}
