package md.mgmt.network;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.recv.find.DirMdAttrPosList;
import md.mgmt.network.recv.find.FileMdAttrPosList;

import java.util.List;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface FindMdDao {

    public RespDto findFileMdIndex(MdIndex mdIndex);

    public RespDto findFileMdAttr(FileMdAttrPosList fileMdAttrPosList);

    public RespDto findDirMdIndex(MdIndex mdIndex);

    public List<MdAttr> findDirMdAttr(DirMdAttrPosList dirMdAttrPosList);
}
