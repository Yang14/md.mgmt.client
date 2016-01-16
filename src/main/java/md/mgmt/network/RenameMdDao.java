package md.mgmt.network;

import md.mgmt.base.ops.RenamedMd;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.recv.find.FileMdAttrPosList;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface RenameMdDao {

    public RespDto renameMdIndex(RenamedMd renamedMd);

    public RespDto renameMdAttr(FileMdAttrPosList fileMdAttrPosList, String newFileName);

}
