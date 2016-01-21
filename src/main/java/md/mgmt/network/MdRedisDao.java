package md.mgmt.network;

/**
 * Created by Mr-yang on 16-1-20.
 */
public interface MdRedisDao {

    public void setObj(String key,String val);

    public String getObj(String key);
}
