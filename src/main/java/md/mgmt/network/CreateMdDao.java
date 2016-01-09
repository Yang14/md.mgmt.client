package md.mgmt.network;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.network.recv.create.index.MdAttrPos;
import md.mgmt.network.recv.create.index.MdAttrPosDto;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface CreateMdDao {

    public CreateMdResp createFileMd(Md md);

    public MdAttrPosDto createFileMdIndex(MdIndex mdIndex);

    public MdAttrPosDto createDirMdIndex(MdIndex mdIndex);

    public CreateMdResp createMdAttr(MdAttrPos mdAttrPos, MdAttr mdAttr);
}
