package pers.ssh.admin.console.commands.impl;

import java.util.List;

import pers.ssh.admin.console.beans.CommandResponse;
import pers.ssh.admin.console.commands.InputAuthCommand;
import pers.ssh.admin.console.daos.CategoryDao;
import pers.ssh.admin.console.daos.impl.CategoryDaoImpl;
import pers.ssh.admin.console.entity.Category;

/**
 * Author:   Dennis Su
 * Date:     2019/12/5 6:52 下午
 * Description:
 */
public class GetTopCategoryCommand extends InputAuthCommand {

    private final CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    protected void setupParameters(final List<String> parameters) {
        // empty
    }

    @Override
    public CommandResponse execute() throws Exception {
        final List<Category> categories = this.categoryDao.findByOrderByListingsDescLimit(3);
        if (categories != null && categories.size() > 0) {
            return CommandResponse.success(categories.get(0).getName());
        }
        return CommandResponse.success();
    }
}
