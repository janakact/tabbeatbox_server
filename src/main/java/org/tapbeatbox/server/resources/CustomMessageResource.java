package org.tapbeatbox.server.resources;

/**
 * This resource will be used when sending a customized message to the front end.
 * @author chathura
 */
public class CustomMessageResource {
    private String msg;

    public CustomMessageResource(String message, String... args) {
        try {
            this.msg = processMsg(message, args);
        } catch (Exception ex) {
            this.msg = message;
        }
    }

    private String processMsg(String message, String... args) throws Exception {
        int index = 0;
        while (message.contains("{}")) {
            message = message.replaceFirst("\\{\\}", args[index++]);
        }
        return message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
