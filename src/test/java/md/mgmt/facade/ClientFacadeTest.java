package md.mgmt.facade;

import md.mgmt.base.md.MdAttr;
import md.mgmt.base.md.MdIndex;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import md.mgmt.service.CreateMdService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mr-yang on 16-1-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ClientFacadeTest {

    @Autowired
    private ClientFacade clientFacade;

    @InjectMocks
    @Autowired
    private CreateMdService createMdService;

//    @Mock
//    private CreateMdDao createMdDao;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateFileMd() {
        MdIndex mdIndex = new MdIndex();
        mdIndex.setPath("/bin");
        mdIndex.setName("foo.txt");
        MdAttr mdAttr = new MdAttr();
        mdAttr.setName("foo.txt");
        mdAttr.setAcl((short) 777);
        Md md = new Md();
        md.setMdIndex(mdIndex);
        md.setMdAttr(mdAttr);

        MdAttrPosDto mdAttrPosDto = new MdAttrPosDto();
        mdAttrPosDto.setSuccess(true);
        mdAttrPosDto.setMsg("get md pos ok.");

        CreateMdResp createMdResp = new CreateMdResp();
        createMdResp.setSuccess(true);
        createMdResp.setMsg(mdAttrPosDto.getMsg());

//        when(createMdService.createFileMd(argThat(new MdArgument()))).thenReturn(createMdResp);
//        when(createMdDao.createFileMdIndex(md.getMdIndex())).thenReturn(mdAttrPosDto);
//        when(createMdDao.createMdAttr(mdAttrPosDto.getMdAttrPos(),md.getMdAttr())).thenReturn(createMdResp);
        System.out.println(clientFacade.createFileMd(md));
       // verify(createMdService).createFileMd(argThat(new MdArgument()));
    }

    class MdArgument extends ArgumentMatcher<Md> {

        @Override
        public boolean matches(Object o) {
            return o.getClass() == Md.class;
        }
    }

}
