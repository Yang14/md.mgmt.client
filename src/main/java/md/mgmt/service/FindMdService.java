package md.mgmt.service;

import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.FindFileMdResp;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface FindMdService {

    public FindFileMdResp findFileMd(MdIndex mdIndex);

    public FindDirMdResp findDirMd(MdIndex mdIndex);
}
