package md.mgmt.network.impl;

import md.mgmt.base.md.ClusterNodeInfo;
import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.FindMdDao;
import md.mgmt.network.connect.BackendClient;
import md.mgmt.network.connect.IndexClient;
import md.mgmt.network.connect.find.FindMdAttrHandler;
import md.mgmt.network.connect.find.FindMdIndexHandler;
import md.mgmt.network.recv.find.DirMdAttrPosList;
import md.mgmt.network.recv.find.FileMdAttrPosList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mr-yang on 16-1-14.
 */
@Component
public class FindMdDaoImpl implements FindMdDao {
    private static final Logger logger = LoggerFactory.getLogger(FindMdDaoImpl.class);
    private IndexClient indexClient = new IndexClient();

    @Override
    public RespDto findFileMdIndex(MdIndex mdIndex) {
        RespDto respDto = new RespDto();
        indexClient.connectAndHandle(new FindMdIndexHandler(mdIndex, respDto));
        return respDto;
    }

    @Override
    public RespDto findFileMdAttr(FileMdAttrPosList fileMdAttrPosList) {
        List<ClusterNodeInfo> clusterNodeInfos = fileMdAttrPosList.getClusterNodeInfos();
        RespDto respDto = new RespDto();
        String fileCode = fileMdAttrPosList.getFileCode();
        for (ClusterNodeInfo nodeInfo : clusterNodeInfos){
            BackendClient client = new BackendClient(nodeInfo.getIp(),nodeInfo.getPort());
            client.connectAndHandle(new FindMdAttrHandler(fileCode,respDto));
            if (respDto.getSuccess()){
                break;
            }
        }
        return respDto;
    }

    @Override
    public DirMdAttrPosList findDirMdIndex(MdIndex mdIndex) {
        return null;
    }

    @Override
    public List<MdAttr> findDirMdAttr(DirMdAttrPosList dirMdAttrPosList) {
        return null;
    }
}
