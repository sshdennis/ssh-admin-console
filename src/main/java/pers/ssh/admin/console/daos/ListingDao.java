package pers.ssh.admin.console.daos;

import pers.ssh.admin.console.entity.Listing;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:17 下午
 * Description: Listing data access interface.
 */
public interface ListingDao {

    public Listing createListing(final Listing listing) throws Exception;

    public Listing findById(Long id);

    public void delete(Listing listing);
}
