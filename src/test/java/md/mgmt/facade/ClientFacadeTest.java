package md.mgmt.facade;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.RenamedMd;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.FindDirMdResp;
import md.mgmt.facade.resp.RenameMdResp;
import md.mgmt.service.CreateMdService;
import md.mgmt.service.FindMdService;
import md.mgmt.service.RenameMdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Mr-yang on 16-1-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ClientFacadeTest {
    private static final Logger logger = LoggerFactory.getLogger(ClientFacadeTest.class);

    @Autowired
    private ClientFacade clientFacade;

    @InjectMocks
    @Autowired
    private CreateMdService createMdService;

    @Autowired
    private FindMdService findMdService;

    @Autowired
    private RenameMdService renameMdService;

    private static CountDownLatch latch = new CountDownLatch(5);

    private MdIndex mdIndex = new MdIndex();
    private MdAttr mdAttr = new MdAttr();
    private Md md = null;

    @Before
    public void initMocks() {
        mdAttr.setAcl((short) 777);
        md = new Md(mdIndex, mdAttr);
    }

    @Test
    public void buildDirTree() {
        long start = System.currentTimeMillis();
        String secondDir = "bin";
        for (int i = 0; i < 5; i++) {
            createMdService.createDirMd(getMd("/", secondDir + i, i, true));

        }

        long end = System.currentTimeMillis();
        logger.info(String.valueOf(System.currentTimeMillis()));
        logger.info(String.format("time: %s", (end - start)));

        String thirdDir = "foo";
        String thirdFile = "a.t";
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                createMdService.createDirMd(getMd("/" + secondDir + i, thirdDir + j + ":" + i, j, true));
                createMdService.createFileMd(getMd("/" + secondDir + i, thirdFile + j, j * 5, false));
            }
        }
        long end2 = System.currentTimeMillis();
        logger.info(String.valueOf(System.currentTimeMillis()));
        logger.info(String.format("time: %s", (end2 - end)));
        logger.info(String.format("total time: %s", (end2 - start)));
    }

    @Test
    public void buildBigDir() {
        int count = 1000;
        logger.info("\n\n\n" + String.valueOf(System.currentTimeMillis()));
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            createMdService.createFileMd(getMd("/home/a", "a-file" + i, i, false));
        }
        long end = System.currentTimeMillis();
        logger.info(String.valueOf(System.currentTimeMillis()));
        logger.info(
                String.format("\nbuildBigDir %s count use Total time: %s ms\navg time: %sms\n\n\n",
                        count, (end - start), (end - start) / (count * 1.0)));
    }

    @Test
    public void testListDirMd() throws InterruptedException {

        for (int i = 0; i < 5; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mdIndex.setPath("/");
                    mdIndex.setName("bin" + finalI);
                    printDirList(findMdService.findDirMd(mdIndex));
                    createMdService.createFileMd(getMd("/bin0", "a-file-redis-sync-set-" + finalI, finalI, false));
                    mdIndex.setPath("/bin0");
                    mdIndex.setName("a.t" + finalI);
                    logger.info(findMdService.findFileMd(mdIndex).toString());
                    latch.countDown();
                }
            }).start();
        }
        latch.await();
    }

    @Test
    public void testListDirMd2() {
        mdIndex.setPath("/");
        mdIndex.setName("bin0");
        printDirList(findMdService.findDirMd(mdIndex));
    }

    private Md getMd(String path, String name, int size, boolean isDir) {
        mdIndex.setPath(path);
        mdIndex.setName(name);
        mdAttr.setName(name);
        mdAttr.setSize(size);
        mdAttr.setType(isDir);
        md.setMdAttr(mdAttr);
        md.setMdIndex(mdIndex);
        return md;
    }

    @Test
    public void testCreateFileMd() {
        createMdService.createFileMd(getMd("/home/a", "a-file" + 84, 84, false));
    }

    @Test
    public void testCreteDirMd() {
        mdIndex.setPath("/bin");
        mdIndex.setName("yang");
        md.setMdIndex(mdIndex);
        md.setMdAttr(mdAttr);
        logger.info(createMdService.createDirMd(md).toString());
    }

    @Test
    public void testFindFileMd() {
        mdIndex.setPath("/bin2");
        mdIndex.setName("a.t4");
        logger.info(findMdService.findFileMd(mdIndex).toString());
    }

    @Test
    public void testRenameMd() throws InterruptedException {
        testListDirMd();
        RenamedMd renamedMd = new RenamedMd("/bin0", "a.t0", "a.t0---0");
        RenameMdResp renameMdResp = renameMdService.renameMd(renamedMd);
        logger.info(renameMdResp.toString());
        testListDirMd();
    }

    private void listDir(String path, String name) {
        mdIndex.setPath("/");
        mdIndex.setName("");
        printDirList(findMdService.findDirMd(mdIndex));
    }

    private void printDirList(FindDirMdResp findDirMdResp) {
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
