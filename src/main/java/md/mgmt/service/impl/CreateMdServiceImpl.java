package md.mgmt.service.impl;

import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.network.CreateMdDao;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import md.mgmt.service.CreateMdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-9.
 */
@Component("createMdService")
public class CreateMdServiceImpl implements CreateMdService {
    private static final Logger logger = LoggerFactory.getLogger(CreateMdServiceImpl.class);

    @Autowired
    private CreateMdDao createMdDao;

    @Override
    public CreateMdResp createFileMd(Md md) {
        MdAttrPosDto mdAttrPosDto = createMdDao.createFileMdIndex(md.getMdIndex());
        logger.info(String.valueOf(mdAttrPosDto));
        if (mdAttrPosDto == null || !mdAttrPosDto.getSuccess()) {
            logger.error("createFileMd error: " + mdAttrPosDto == null ? "null return." : mdAttrPosDto.getMsg());
            return null;
        }
        return createMdDao.createMdAttr(mdAttrPosDto.getMdAttrPos(),md.getMdAttr());
    }

    @Override
    public CreateMdResp createDirMd(Md md) {
        return null;
    }
}
