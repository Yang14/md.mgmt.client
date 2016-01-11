package md.mgmt.service;

import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.FindFileMdResp;
import md.mgmt.base.md.MdIndex;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface FindMdService {

    public FindFileMdResp findFileMd(MdIndex mdIndex);

    public FindDirMdResp findDirMd(MdIndex mdIndex);
}
