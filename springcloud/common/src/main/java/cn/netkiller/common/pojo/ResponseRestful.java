package cn.netkiller.common.pojo;

import java.io.Serializable;

public class ResponseRestful implements Serializable {
    private static final long serialVersionUID = -4045645995352698349L;

    private boolean status;
    private String reason;
    private int code;

    private Object data;

    public ResponseRestful() {

    }

    public ResponseRestful(boolean status, int code, String reason, Object data) {
        this.status = status;
        this.code = code;
        this.reason = reason;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RestfulResponse [status=" + status + ", reason=" + reason + ", code=" + code + ", data=" + data.toString() + "]";
    }

}
