package md.mgmt.service.impl;

import com.alibaba.fastjson.JSON;
import md.mgmt.base.ops.RenamedMd;
import md.mgmt.base.ops.RespDto;
import md.mgmt.facade.resp.RenameMdResp;
import md.mgmt.network.RenameMdDao;
import md.mgmt.network.recv.find.FileMdAttrPosList;
import md.mgmt.service.RenameMdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-16.
 */
@Component
public class RenameMdServiceImpl implements RenameMdService {
    private static final Logger logger = LoggerFactory.getLogger(RenameMdServiceImpl.class);

    @Autowired
    private RenameMdDao renameMdDao;

    @Override
    public RenameMdResp renameMd(RenamedMd renamedMd) {
        RenameMdResp renameMdResp = new RenameMdResp();
        RespDto respDto = renameMdDao.renameMdIndex(renamedMd);
        if (respDto == null || !respDto.getSuccess()) {
            logger.error("renameMdIndex error, params: " + renamedMd);
            return null;
        }
        FileMdAttrPosList fileMdAttrPosList =
                JSON.parseObject(respDto.getObjStr(), FileMdAttrPosList.class);
        if (fileMdAttrPosList == null){
            logger.error("fileMdAttrPosList is null.params:" + renamedMd);
            return null;
        }
        respDto = renameMdDao.renameMdAttr(fileMdAttrPosList, renamedMd.getNewName());
        if (respDto == null || !respDto.getSuccess()) {
            logger.error("renameMdAttr error, params: " + renamedMd);
            return null;
        }
        BeanUtils.copyProperties(respDto,renameMdResp);
        return renameMdResp;
    }
}
