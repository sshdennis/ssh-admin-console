package pers.ssh.admin.console.commands.impl;

import java.util.List;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.daos.UserDao;
import pers.ssh.admin.console.daos.impl.ListingDaoImpl;
import pers.ssh.admin.console.daos.impl.UserDaoImpl;
import pers.ssh.admin.console.entity.Listing;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:59 上午
 * Description:
 */
public class CreateListingCommand extends InputAuthCommand {

    private final UserDao userDao = new UserDaoImpl();
    private final ListingDao listingDao = new ListingDaoImpl();

    private String userName;
    private String title;
    private String description;
    private long price;
    private String category;

    @Override
    protected void setupParameters(final List<String> parameters) {
        this.userName = parameters.get(0);
        this.title = parameters.get(1);
        this.description = parameters.get(2);
        this.price = Long.valueOf(parameters.get(3));
        this.category = parameters.get(4);
    }

    @Override
    public CommandResponse execute() throws Exception {
        final Listing listing = new Listing();
        listing.setUserName(this.userName);
        listing.setTitle(this.title);
        listing.setDescription(this.description);
        listing.setPrice(this.price);
        listing.setCategory(this.category);
        this.listingDao.createListing(listing);

        return CommandResponse.success(String.valueOf(listing.getId()));
    }
}
