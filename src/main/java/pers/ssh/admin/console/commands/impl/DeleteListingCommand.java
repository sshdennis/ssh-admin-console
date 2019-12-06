package pers.ssh.admin.console.commands.impl;

import java.util.List;
import java.util.Objects;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.daos.UserDao;
import pers.ssh.admin.console.daos.impl.ListingDaoImpl;
import pers.ssh.admin.console.daos.impl.UserDaoImpl;
import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   dsu01
 * Date:     2019/12/5 12:07 上午
 * Description:
 */
public class DeleteListingCommand extends InputAuthCommand {

    private final UserDao userDao = new UserDaoImpl();
    private final ListingDao listingDao = new ListingDaoImpl();

    private String userName;
    private long listingId;

    @Override
    protected void setupParameters(final List<String> parameters) {
        this.userName = parameters.get(0);
        this.listingId = Long.valueOf(parameters.get(1));
    }

    @Override
    public CommandResponse execute() throws Exception {
        final Listing listing = this.listingDao.findById(this.listingId);
        if (listing == null) {
            throw new Exception("listing does not exist");
        }
        Logger.debug(listing.toString());

        if (!Objects.equals(this.authUser.getName(), listing.getUserName())) {
            throw new Exception("listing owner mismatch");
        }
        this.listingDao.delete(listing);

        return CommandResponse.success();
    }
}
