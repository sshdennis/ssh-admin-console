package pers.ssh.admin.console.daos;

import pers.ssh.admin.console.entity.Category;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 2:18 下午
 * Description: Category data access interface.
 */
public interface CategoryDao {

    public Category findOneByOrderByListingsDescLimit();
}
