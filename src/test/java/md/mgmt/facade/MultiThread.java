package md.mgmt.facade;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.service.FindMdService;
import md.mgmt.service.impl.FindMdServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Mr-yang on 16-1-21.
 */
public class MultiThread implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientFacadeTest.class);

    private FindMdService findMdService = new FindMdServiceImpl();

    @Override
    public void run() {
        MdIndex mdIndex = new MdIndex();
        mdIndex.setPath("/");
        mdIndex.setName("bin0");
        printDirList(findMdService.findDirMd(mdIndex));
    }

    public static void main(String[] args) throws Exception {
        for (int i=0;i<5;i++){
            new Thread(new MultiThread()).start();
        }
    }

    private static void printDirList(FindDirMdResp findDirMdResp) {
        List<MdAttr> mdAttrs = findDirMdResp.getMdAttrs();
        int k = 0;
        for (MdAttr mdAttr1 : mdAttrs) {
            if (k++ % 10 == 0) {
                logger.info("");
            }
            logger.info(String.format("[%s,%s]", mdAttr1.getName(), mdAttr1.getSize()));
        }
    }
}
