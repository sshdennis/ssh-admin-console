package pers.ssh.admin.console.beans;

/**
 * Author:   Dennis Su
 * Date:     2019/12/4 12:48 上午
 * Description: For Command process response.
 */
public class CommandResponse {

    public static String RES_SUCCESS = "Success";

    private boolean isSuccess = false;
    private String response;

    /**
     * Return success CommandResponse with success message.
     *
     * @return
     */
    public static CommandResponse success() {
        return success(RES_SUCCESS);
    }

    /**
     * Return success CommandResponse with the given responseMsg.
     *
     * @param responseMsg
     * @return
     */
    public static CommandResponse success(final String responseMsg) {
        final CommandResponse res = new CommandResponse();
        res.setSuccess(true);
        res.setResponse(responseMsg);
        return res;
    }

    /**
     * Return error CommandResponse with the given responseMsg.
     *
     * @param responseMsg
     * @return
     */
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
