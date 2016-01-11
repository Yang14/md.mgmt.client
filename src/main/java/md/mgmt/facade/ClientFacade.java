package md.mgmt.facade;

import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.FindFileMdResp;
import md.mgmt.facade.resp.RenameMdResp;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RenamedMd;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface ClientFacade {

    public CreateMdResp createFileMd(Md md);

    public CreateMdResp createDirMd(Md md);

    public FindFileMdResp findFileMd(MdIndex mdIndex);

    public FindDirMdResp findDirMd(MdIndex mdIndex);

    public RenameMdResp renameMd(RenamedMd renamedMd);
}
