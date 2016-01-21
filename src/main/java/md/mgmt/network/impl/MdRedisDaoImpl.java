package md.mgmt.network.impl;

import md.mgmt.network.MdRedisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/**
 * Created by Mr-yang on 16-1-20.
 */
@Component
public class MdRedisDaoImpl implements MdRedisDao {
    private static final Logger logger = LoggerFactory.getLogger(MdRedisDaoImpl.class);

    @Autowired
    private Jedis jedis;

    @Override
    public void setObj(String key, String val) {
        jedis.set(key,val);
    }

    @Override
    public String getObj(String key) {
        return jedis.get(key);
    }
}
