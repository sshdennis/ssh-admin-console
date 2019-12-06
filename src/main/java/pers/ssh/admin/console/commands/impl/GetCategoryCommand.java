package pers.ssh.admin.console.commands.impl;

import java.util.List;
import java.util.stream.Collectors;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.constants.CategoryOrderBy;
import pers.ssh.admin.console.constants.CategorySortBy;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.daos.impl.ListingDaoImpl;
import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.entity.converter.ListingConverter;
import pers.ssh.admin.console.utils.StringUtils;

/**
 * Author:   dsu01
 * Date:     2019/12/5 11:50 上午
 * Description:
 */
public class GetCategoryCommand extends InputAuthCommand {

    private final ListingDao listingDao = new ListingDaoImpl();

    private String userName;
    private String category;
    private CategorySortBy sortBy;
    private CategoryOrderBy orderBy;

    @Override
    protected void setupParameters(final List<String> parameters) {
        this.userName = parameters.get(0);
        this.category = parameters.get(1);

        if (parameters.size() >= 3) {
            final String sortByStr = parameters.get(2);
            if (StringUtils.isNotBlank(sortByStr)) {
                this.sortBy = CategorySortBy.findByValue(sortByStr);
            }
        }
        if (parameters.size() >= 4) {
            final String orderByStr = parameters.get(3);
            if (StringUtils.isNotBlank(orderByStr)) {
                this.orderBy = CategoryOrderBy.findByValue(orderByStr);
            }
        }
    }

    @Override
    public CommandResponse execute() throws Exception {
        final List<Listing> listings = this.listingDao.findByCategory(this.category, this.sortBy, this.orderBy);

        final String respListings = listings.stream().map(ListingConverter::toCategoryString).collect(Collectors.joining("\n"));
        return CommandResponse.success(respListings);
    }
}
