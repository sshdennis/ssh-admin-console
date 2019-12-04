package pers.ssh.admin.console.daos.impl;

import pers.ssh.admin.console.daos.GlobalDataPool;
import pers.ssh.admin.console.daos.ListingDao;
import pers.ssh.admin.console.entity.Listing;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:19 下午
 * Description: Listing data access object.
 */
public class ListingDaoImpl implements ListingDao {

    @Override
    public Listing createListing(final Listing listing) throws Exception {
        return GlobalDataPool.createListing(listing);
    }

    @Override
    public Listing findById(final Long id) {
        return GlobalDataPool.findListingById(id);
    }

    @Override
    public void delete(final Listing listing) {
        GlobalDataPool.deleteListing(listing);
    }
}
