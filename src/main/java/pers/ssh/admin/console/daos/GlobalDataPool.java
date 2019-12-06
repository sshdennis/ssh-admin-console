package pers.ssh.admin.console.daos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import pers.ssh.admin.console.constants.CategoryOrderBy;
import pers.ssh.admin.console.constants.CategorySortBy;
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

    private static final Map<String, Category> listingCategoryMapping = new HashMap<>();

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

        // listing
        final long newId = listingId.incrementAndGet();
        listing.setId(newId);
        listing.setCreatedAt(new Date());
        listingMap.put(listing.getId(), listing);

        // user
        existUser.getListings().add(listing);

        // category
        Category category = listingCategoryMapping.get(listing.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(listing.getCategory());
            listingCategoryMapping.put(listing.getCategory(), category);
        }
        category.getListings().add(listing);

        return listing;
    }

    public static Listing findListingById(final Long listingId) {
        return listingMap.get(listingId);
    }

    public static void deleteListing(final Listing listing) {
        listingMap.remove(listing.getId());

        // user
        final User user = findUserByName(listing.getUserName());
        for (final Iterator<Listing> it = user.getListings().iterator(); it.hasNext(); ) {
            final Listing lis = it.next();
            if (Objects.equals(lis.getId(), listing.getId())) {
                it.remove();
                break;
            }
        }

        // category
        final Category category = listingCategoryMapping.get(listing.getCategory());
        for (final Iterator<Listing> it = category.getListings().iterator(); it.hasNext(); ) {
            final Listing lis = it.next();
            if (Objects.equals(lis.getId(), listing.getId())) {
                it.remove();
                break;
            }
        }
    }

    public static List<Listing> findListingByCategory(final String categoryName, final CategorySortBy sortBy, final CategoryOrderBy orderBy) {
        final Category category = listingCategoryMapping.get(categoryName);

        final List<Listing> listings = category.getListings();
        if (sortBy != null && orderBy != null) {
            Comparator comparator = null;
            switch (sortBy) {
                case PRICE:
                    comparator = Comparator.comparing(Listing::getPrice);
                    break;
                case TIME:
                    comparator = Comparator.comparing(Listing::getCreatedAt);
                    break;
                default:
                    break;
            }
            switch (orderBy) {
                case DSC:
                    comparator = comparator.reversed();
                    break;
                default:
                    break;
            }
            listings.sort(comparator);
        }
        return listings;
    }

    public static List<Category> findCategoryOrderByListingsDescLimit(final int limit) {
        final List<Category> categories = new ArrayList<>(listingCategoryMapping.values());
        if (categories == null || categories.size() == 0) {
            return new ArrayList<>();
        }
//        Comparator<Category> comp = Comparator.comparing(e -> e.getListings().size()).reversed();

        final Comparator<Category> comp = Comparator.comparing(e -> e.getListings().size());
        categories.sort(comp.reversed());


        return categories.subList(0, limit > categories.size() ? categories.size() : limit);
    }
}
