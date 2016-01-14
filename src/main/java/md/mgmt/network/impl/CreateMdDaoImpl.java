package md.mgmt.network.impl;

import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.network.CreateMdDao;
import md.mgmt.network.connect.BackendClient;
import md.mgmt.network.connect.IndexClient;
import md.mgmt.network.connect.create.CreateMdIndexHandler;
import md.mgmt.network.recv.create.index.MdAttrPos;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.network.connect.create.CreateMdAttrHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-9.
 */
@Component
public class CreateMdDaoImpl implements CreateMdDao {
    private static final Logger logger = LoggerFactory.getLogger(CreateMdDaoImpl.class);
    private IndexClient indexClient = new IndexClient();

    @Override
    public CreateMdResp createFileMd(Md md) {
        return null;
    }

    @Override
    public MdAttrPosDto createFileMdIndex(MdIndex mdIndex) {
        MdAttrPosDto mdAttrPosDto = new MdAttrPosDto();
        try {
            indexClient.connectAndHandle(
                    new CreateMdIndexHandler(mdIndex, mdAttrPosDto, OpsTypeEnum.CREATE_FILE.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return mdAttrPosDto;
    }

    @Override
    public MdAttrPosDto createDirMdIndex(MdIndex mdIndex) {
        MdAttrPosDto mdAttrPosDto = new MdAttrPosDto();
        try {
            indexClient.connectAndHandle(
                    new CreateMdIndexHandler(mdIndex, mdAttrPosDto, OpsTypeEnum.CREATE_DIR.getCode()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return mdAttrPosDto;
    }

    @Override
    public CreateMdResp createMdAttr(MdAttrPos mdAttrPos, MdAttr mdAttr) {
        CreateMdResp createMdResp = new CreateMdResp();
        createMdResp.setSuccess(false);
        createMdResp.setMsg("创建文件元数据失败");
        String ip = mdAttrPos.getClusterNodeInfo().getIp();
        int port = mdAttrPos.getClusterNodeInfo().getPort();
        BackendClient backendClient = new BackendClient(ip, port);
        CreateMdAttrHandler createMdAttrHandler =
                new CreateMdAttrHandler(mdAttr, mdAttrPos.getExactCode(), createMdResp);
        try {
            backendClient.connectAndHandle(createMdAttrHandler);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return createMdResp;
    }
}
