package md.mgmt.facade.req;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class Md {

    private MdIndex mdIndex;

    private MdAttr mdAttr;

    public MdIndex getMdIndex() {
        return mdIndex;
    }

    public void setMdIndex(MdIndex mdIndex) {
        this.mdIndex = mdIndex;
    }

    public MdAttr getMdAttr() {
        return mdAttr;
    }

    public void setMdAttr(MdAttr mdAttr) {
        this.mdAttr = mdAttr;
    }

    @Override
    public String toString() {
        return "Md{" +
                "mdIndex=" + mdIndex +
                ", mdAttr=" + mdAttr +
                '}';
    }
}
