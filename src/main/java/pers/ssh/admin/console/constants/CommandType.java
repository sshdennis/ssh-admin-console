package pers.ssh.admin.console.constants;

/**
 * Author:   Dennis Su
 * Date:     2019/12/1 10:37 上午
 * Description: The input command.
 */
public enum CommandType {
    // user
    REGISTER,

    // listing
    CREATE_LISTING,
    GET_LISTING,
    DELETE_LISTING,

    // category
    GET_CATEGORY,
    GET_TOP_CATEGORY,

    // exit
    EXIT
}
