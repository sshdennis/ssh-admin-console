package pers.ssh.admin.console.commands.impl;

import java.util.List;
import java.util.Objects;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.constants.ErrorMessage;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.daos.impl.ListingDaoImpl;
import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 12:07 上午
 * Description:
 */
public class DeleteListingCommand extends InputAuthCommand {

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
            throw new Exception(ErrorMessage.LISTING_NOT_EXISTING);
        }
        Logger.debug(listing.toString());

        if (!Objects.equals(this.authUser.getName().toLowerCase(), listing.getUserName().toLowerCase())) {
            throw new Exception(ErrorMessage.LISTING_OWNER_MISMATCH);
        }
        this.listingDao.delete(listing);

        return CommandResponse.success();
    }
}
