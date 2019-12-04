package pers.ssh.admin.console.daos;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import pers.ssh.admin.console.entity.Category;
import pers.ssh.admin.console.entity.Listing;
import pers.ssh.admin.console.entity.User;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:13 下午
 * Description: The data storage. This is NOT thread safe.
 */
public class GlobalDataPool {

    private static final AtomicLong userId = new AtomicLong(0);
    private static final Map<Long, User> userMap = new HashMap<>();

    private static final AtomicLong listingId = new AtomicLong(100000);
    private static final Map<Long, Listing> listingMap = new HashMap<>();

    private static final AtomicLong categoryId = new AtomicLong(0);
    private static final Map<Long, Category> categoryMap = new HashMap<>();

    public static User createUser(final User user) throws Exception {
        final User existUser = findUserByName(user.getName());
        if (existUser != null) {
            throw new Exception("user already existing");
        }
        final long newId = userId.incrementAndGet();
        user.setId(newId);
        userMap.put(user.getId(), user);
        return user;
    }

    public static User findUserByName(final String userName) {
        final List<User> us = userMap.values().stream()
                .filter(e -> e.getName().equalsIgnoreCase(userName)).collect(Collectors.toList());
        if (us == null || us.size() == 0) {
            return null;
        }
        return us.get(0);
    }

    public static Listing createListing(final Listing listing) throws Exception {
        final User existUser = findUserByName(listing.getUserName());
        if (existUser == null) {
            throw new Exception("unknown user");
        }
        final long newId = listingId.incrementAndGet();
        listing.setId(newId);
        listing.setCreatedAt(new Date());
        existUser.getListings().add(listing);
        listingMap.put(listing.getId(), listing);
        return listing;
    }

    public static Listing findListingById(final Long listingId) {
        return listingMap.get(listingId);
    }

    public static void deleteListing(final Listing listing) {
        listingMap.remove(listing.getId());

        final User user = findUserByName(listing.getUserName());
        for (final Iterator<Listing> it = user.getListings().iterator(); it.hasNext(); ) {
            final Listing lis = it.next();
            if (Objects.equals(lis.getId(), listing.getId())) {
                it.remove();
                break;
            }
        }
    }
}
