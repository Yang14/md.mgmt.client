package md.mgmt.service;

import md.mgmt.base.ops.RenamedMd;
import md.mgmt.facade.resp.RenameMdResp;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface RenameMdService {

    public RenameMdResp renameMd(RenamedMd renamedMd);
}
