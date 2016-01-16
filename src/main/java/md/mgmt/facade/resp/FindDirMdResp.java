package md.mgmt.facade.resp;

import md.mgmt.base.md.MdAttr;

import java.util.List;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class FindDirMdResp {

    private Boolean success;
    private String msg;
    private List<MdAttr> mdAttrs;

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

    public List<MdAttr> getMdAttrs() {
        return mdAttrs;
    }

    public void setMdAttrs(List<MdAttr> mdAttrs) {
        this.mdAttrs = mdAttrs;
    }

    @Override
    public String toString() {
        return "FindDirMdResp{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", mdAttrs=" + mdAttrs +
                '}';
    }
}
