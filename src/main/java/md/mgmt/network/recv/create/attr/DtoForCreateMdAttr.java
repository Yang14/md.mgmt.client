package md.mgmt.network.recv.create.attr;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class DtoForCreateMdAttr {

    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "DtoForCreateMdAttr{" +
                "success=" + success +
                '}';
    }
}
