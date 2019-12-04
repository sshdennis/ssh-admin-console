package pers.ssh.admin.console.beans;

import pers.ssh.admin.console.constants.AdminConsoleConstants;

/**
 * Author:   dsu01
 * Date:     2019/12/4 12:48 上午
 * Description: For Command process response.
 */
public class CommandResponse {

    private boolean isSuccess = false;
    private String response;

    public static CommandResponse success() {
        return success(AdminConsoleConstants.RES_SUCCESS);
    }

    public static CommandResponse success(final String responseMsg) {
        final CommandResponse res = new CommandResponse();
        res.setSuccess(true);
        res.setResponse(responseMsg);
        return res;
    }

    public static CommandResponse error(final String responseMsg) {
        final CommandResponse res = new CommandResponse();
        res.setSuccess(false);
        res.setResponse(responseMsg);
        return res;
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setSuccess(final boolean success) {
        this.isSuccess = success;
    }

    public String getResponse() {
        return this.response;
    }

    public void setResponse(final String response) {
        this.response = response;
    }
}
