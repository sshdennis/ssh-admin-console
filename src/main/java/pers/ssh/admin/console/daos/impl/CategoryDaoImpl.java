package pers.ssh.admin.console.daos.impl;

import java.util.List;

import pers.ssh.admin.console.daos.CategoryDao;
import pers.ssh.admin.console.daos.GlobalDataPool;
import pers.ssh.admin.console.entity.Category;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:19 下午
 * Description: Category data access object.
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findByOrderByListingsDescLimit(int limit) {
        return GlobalDataPool.findCategoryOrderByListingsDescLimit(limit);
    }
}
