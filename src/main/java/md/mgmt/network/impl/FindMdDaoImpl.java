package md.mgmt.network.impl;

import com.alibaba.fastjson.JSON;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.md.ClusterNodeInfo;
import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.FindMdDao;
import md.mgmt.network.connect.BackendClient;
import md.mgmt.network.connect.IndexClient;
import md.mgmt.network.connect.find.FindDirMdAttrHandler;
import md.mgmt.network.connect.find.FindFileMdAttrHandler;
import md.mgmt.network.connect.find.FindMdIndexHandler;
import md.mgmt.network.recv.find.DirMdAttrPosList;
import md.mgmt.network.recv.find.FileMdAttrPosList;
import md.mgmt.network.recv.find.MdAttrListDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr-yang on 16-1-14.
 */
@Component
public class FindMdDaoImpl implements FindMdDao {
    private static final Logger logger = LoggerFactory.getLogger(FindMdDaoImpl.class);

    @Override
    public RespDto findFileMdIndex(MdIndex mdIndex) {
        RespDto respDto = new RespDto();
        IndexClient.connectAndHandle(new FindMdIndexHandler(mdIndex, respDto, OpsTypeEnum.FIND_FILE.getCode()));
        return respDto;
    }

    @Override
    public RespDto findFileMdAttr(FileMdAttrPosList fileMdAttrPosList) {
        List<ClusterNodeInfo> clusterNodeInfos = fileMdAttrPosList.getClusterNodeInfos();
        RespDto respDto = new RespDto();
        String fileCode = fileMdAttrPosList.getFileCode();
        for (ClusterNodeInfo nodeInfo : clusterNodeInfos) {
            BackendClient.connectAndHandle(nodeInfo.getIp(), nodeInfo.getPort(),
                    new FindFileMdAttrHandler(fileCode, respDto));
            if (respDto.getSuccess()) {
                break;
            }
        }
        return respDto;
    }

    @Override
    public RespDto findDirMdIndex(MdIndex mdIndex) {
        RespDto respDto = new RespDto();
        IndexClient.connectAndHandle(new FindMdIndexHandler(mdIndex, respDto, OpsTypeEnum.LIST_DIR.getCode()));
        return respDto;
    }

    @Override
    public List<MdAttr> findDirMdAttr(DirMdAttrPosList dirMdAttrPosList) {
        List<ClusterNodeInfo> clusterNodeInfos = dirMdAttrPosList.getClusterNodeInfos();
        RespDto respDto = new RespDto();
        List<MdAttr> mdAttrs = new ArrayList<MdAttr>();
        for (ClusterNodeInfo nodeInfo : clusterNodeInfos) {
            BackendClient.connectAndHandle(nodeInfo.getIp(), nodeInfo.getPort(),
                    new FindDirMdAttrHandler(nodeInfo.getDistrCode(), respDto));
            if (!respDto.getSuccess()) {
                logger.error(String.format("Get mdAttr from Node: %s err. resp is:%s", nodeInfo, respDto));
            }
            MdAttrListDto partMdAttrs = JSON.parseObject(respDto.getObjStr(), MdAttrListDto.class);
            for (MdAttr mdAttr : partMdAttrs.getMdAttrList()) {
                mdAttrs.add(mdAttr);
            }
        }
        return mdAttrs;
    }
}
