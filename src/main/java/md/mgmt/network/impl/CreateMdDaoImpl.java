package md.mgmt.network.impl;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.network.CreateMdDao;
import md.mgmt.network.connect.create.CreateMdClient;
import md.mgmt.network.recv.create.index.MdAttrPos;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-9.
 */
@Component
public class CreateMdDaoImpl implements CreateMdDao {
    private static final Logger logger = LoggerFactory.getLogger(CreateMdDaoImpl.class);

    @Override
    public CreateMdResp createFileMd(Md md) {
        CreateMdResp createMdResp = new CreateMdResp();
        createMdResp.setSuccess(false);
        createMdResp.setMsg("创建文件元数据失败");
        try {
            new CreateMdClient().connectToServer(md, createMdResp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return createMdResp;
    }

    @Override
    public MdAttrPosDto createFileMdIndex(MdIndex mdIndex) {

        return null;
    }

    @Override
    public MdAttrPosDto createDirMdIndex(MdIndex mdIndex) {
        return null;
    }

    @Override
    public CreateMdResp createMdAttr(MdAttrPos mdAttrPos, MdAttr mdAttr) {
        return null;
    }
}
