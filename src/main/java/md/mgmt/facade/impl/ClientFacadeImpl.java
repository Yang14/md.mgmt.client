package md.mgmt.facade.impl;

import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RenamedMd;
import md.mgmt.facade.ClientFacade;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.FindFileMdResp;
import md.mgmt.facade.resp.RenameMdResp;
import md.mgmt.service.CreateMdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-9.
 */
@Component
public class ClientFacadeImpl implements ClientFacade {
    private static final Logger logger = LoggerFactory.getLogger(ClientFacadeImpl.class);

    @Autowired
    private CreateMdService createMdService;

    @Override
    public CreateMdResp createFileMd(Md md) {
        if (md == null){
            logger.error("createFileMd params is null.");
            return null;
        }
        return createMdService.createFileMd(md);
    }

    @Override
    public CreateMdResp createDirMd(Md md) {
        return null;
    }

    @Override
    public FindFileMdResp findFileMd(MdIndex mdIndex) {
        return null;
    }

    @Override
    public FindDirMdResp findDirMd(MdIndex mdIndex) {
        return null;
    }

    @Override
    public RenameMdResp renameMd(RenamedMd renamedMd) {
        return null;
    }
}
