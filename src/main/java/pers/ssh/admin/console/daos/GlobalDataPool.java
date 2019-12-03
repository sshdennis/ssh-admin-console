package pers.ssh.admin.console.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static final AtomicLong listingId = new AtomicLong(0);
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
}
