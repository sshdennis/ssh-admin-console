package pers.ssh.admin.console.commands.impl;

import java.util.List;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.constants.ErrorMessage;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.daos.impl.ListingDaoImpl;
import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.entity.converter.ListingConverter;
import pers.ssh.admin.console.utils.Logger;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 12:24 上午
 * Description:
 */
public class GetListingCommand extends InputAuthCommand {

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
            throw new Exception(ErrorMessage.RESOURCE_NOT_FOUND);
        }

        Logger.debug(listing.toString());
        return CommandResponse.success(ListingConverter.toString(listing));
    }
}
