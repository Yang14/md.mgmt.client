package md.mgmt.facade.resp;

import md.mgmt.base.md.MdAttr;

import java.util.ArrayList;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class FindDirMdResp {

    private Boolean success;
    private String msg;
    private ArrayList<MdAttr> mdAttrs;

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

    public ArrayList<MdAttr> getMdAttrs() {
        return mdAttrs;
    }

    public void setMdAttrs(ArrayList<MdAttr> mdAttrs) {
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
