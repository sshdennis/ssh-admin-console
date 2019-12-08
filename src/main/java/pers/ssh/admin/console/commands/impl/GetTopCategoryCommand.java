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
        final Category category = this.categoryDao.findOneByOrderByListingsDescLimit();
        if (category != null) {
            return CommandResponse.success(category.getName());
        }
        return CommandResponse.success();
    }
}
