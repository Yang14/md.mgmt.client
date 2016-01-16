package md.mgmt.network.impl;

import md.mgmt.base.md.ClusterNodeInfo;
import md.mgmt.base.ops.RenamedMd;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.RenameMdDao;
import md.mgmt.network.connect.BackendClient;
import md.mgmt.network.connect.IndexClient;
import md.mgmt.network.connect.rename.RenameMdAttrHandler;
import md.mgmt.network.connect.rename.RenameMdIndexHandler;
import md.mgmt.network.recv.find.FileMdAttrPosList;
import md.mgmt.network.recv.rename.RenameMdAttrDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Mr-yang on 16-1-16.
 */
@Component
public class RenameMdDaoImpl implements RenameMdDao {
    private static final Logger logger = LoggerFactory.getLogger(FindMdDaoImpl.class);

    private IndexClient indexClient = new IndexClient();

    @Override
    public RespDto renameMdIndex(RenamedMd renamedMd) {
        RespDto respDto = new RespDto();
        indexClient.connectAndHandle(new RenameMdIndexHandler(renamedMd, respDto));
        return respDto;
    }

    @Override
    public RespDto renameMdAttr(FileMdAttrPosList fileMdAttrPosList, String newFileName) {
        List<ClusterNodeInfo> clusterNodeInfos = fileMdAttrPosList.getClusterNodeInfos();
        RespDto respDto = new RespDto();
        String fileCode = fileMdAttrPosList.getFileCode();
        for (ClusterNodeInfo nodeInfo : clusterNodeInfos) {
            BackendClient client = new BackendClient(nodeInfo.getIp(), nodeInfo.getPort());
            client.connectAndHandle(new RenameMdAttrHandler(new RenameMdAttrDto(fileCode, newFileName), respDto));
            if (respDto.getSuccess()) {
                break;
            }
        }
        return respDto;
    }
}
