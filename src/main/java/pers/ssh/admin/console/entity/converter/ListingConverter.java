package pers.ssh.admin.console.entity.converter;

import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.utils.DateTimeUtils;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 6:48 下午
 * Description:
 */
public class ListingConverter {

    /**
     * Convert the given Listing instance to String format.
     *
     * @param listing
     * @return
     */
    public static String toString(final Listing listing) {
        return listing.getTitle() + "|" + listing.getDescription() + "|" + listing.getPrice() + "|" +
                DateTimeUtils.formatDate(listing.getCreatedAt()) + "|" + listing.getCategory() + "|" + listing.getUserName();
    }

    /**
     * Convert the given Listing instance to String with Category format.
     *
     * @param listing
     * @return
     */
    public static String toCategoryString(final Listing listing) {
        return listing.getTitle() + "|" + listing.getDescription() + "|" + listing.getPrice() + "|" +
                DateTimeUtils.formatDate(listing.getCreatedAt());
    }
}
