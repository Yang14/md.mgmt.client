package md.mgmt.facade;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.req.Md;
import md.mgmt.service.CreateMdService;
import md.mgmt.service.FindMdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    //    @Mock
//    private CreateMdDao createMdDao;
    private Md md = new Md();
    private MdIndex mdIndex = new MdIndex();
    private MdAttr mdAttr = new MdAttr();

    @Before
    public void initMocks() {
        mdIndex.setPath("/bin");
        mdIndex.setName("foo.txt");

        mdAttr.setName("foo.txt");
        mdAttr.setAcl((short) 777);
    }

    @Test
    public void testCreateFileMd() {
        md.setMdAttr(mdAttr);
        md.setMdIndex(mdIndex);
        System.out.println(clientFacade.createFileMd(md));
        /*int count = 10;
        System.out.println("\n\n\n" + String.valueOf(System.currentTimeMillis()));
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            System.out.println(clientFacade.createFileMd(md));
        }
        long end = System.currentTimeMillis();
        System.out.println(String.valueOf(System.currentTimeMillis()));
        System.out.println(
                String.format("\nCreate %s dir use Total time: %s ms\navg time: %sms\n\n\n",
                        count, (end - start), (end - start) / (count * 1.0)));*/
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
        mdIndex.setPath("/bin");
        mdIndex.setName("foo.txt");
        logger.info(findMdService.findFileMd(mdIndex).toString());
    }
}
