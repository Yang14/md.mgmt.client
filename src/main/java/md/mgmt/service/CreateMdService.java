package md.mgmt.service;

import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;

/**
 * Created by Mr-yang on 16-1-9.
 */
public interface CreateMdService {

    public CreateMdResp createFileMd(Md md);

    public CreateMdResp createDirMd(Md md);
}
