package md.mgmt.service.impl;

import com.alibaba.fastjson.JSON;
import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RespDto;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.FindFileMdResp;
import md.mgmt.network.FindMdDao;
import md.mgmt.network.recv.find.FileMdAttrPosList;
import md.mgmt.service.FindMdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-14.
 */
@Component
public class FindMdServiceImpl implements FindMdService {
    private static final Logger logger = LoggerFactory.getLogger(FindMdServiceImpl.class);

    @Autowired
    private FindMdDao findMdDao;

    @Override
    public FindFileMdResp findFileMd(MdIndex mdIndex) {
        RespDto respDto = findMdDao.findFileMdIndex(mdIndex);
        if (respDto == null || !respDto.getSuccess()) {
            logger.error("findFileMd error, params: " + mdIndex);
            return null;
        }
        FileMdAttrPosList fileMdAttrPosList =
                JSON.parseObject(respDto.getObjStr(), FileMdAttrPosList.class);
        if (fileMdAttrPosList == null){
            logger.error("fileMdAttrPosList is null.params:" + mdIndex);
            return null;
        }
        FindFileMdResp findFileMdResp = new FindFileMdResp();
        RespDto mdAttrRespDto = findMdDao.findFileMdAttr(fileMdAttrPosList);
        MdAttr mdAttr = JSON.parseObject(mdAttrRespDto.getObjStr(),MdAttr.class);
        findFileMdResp.setMdAttr(mdAttr);
        BeanUtils.copyProperties(mdAttrRespDto, findFileMdResp);
        return findFileMdResp;
    }

    @Override
    public FindDirMdResp findDirMd(MdIndex mdIndex) {
        return null;
    }
}
