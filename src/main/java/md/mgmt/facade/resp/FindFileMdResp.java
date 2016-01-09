package md.mgmt.facade.resp;

import md.mgmt.base.md.MdAttr;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class FindFileMdResp {

    private Boolean success;
    private String msg;
    private MdAttr mdAttr;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MdAttr getMdAttr() {
        return mdAttr;
    }

    public void setMdAttr(MdAttr mdAttr) {
        this.mdAttr = mdAttr;
    }

    @Override
    public String toString() {
        return "FindFileMdResp{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", mdAttr=" + mdAttr +
                '}';
    }
}
