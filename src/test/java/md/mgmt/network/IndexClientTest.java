package md.mgmt.network;

import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.md.MdIndex;
import md.mgmt.network.connect.IndexClient;
import md.mgmt.network.connect.create.CreateMdIndexHandler;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import org.junit.Test;

/**
 * Created by Mr-yang on 16-1-11.
 */
public class IndexClientTest {

    @Test
    public void testCreateFileMdIndex() throws Exception {
        MdAttrPosDto mdAttrPosDto = new MdAttrPosDto();
        System.out.println(mdAttrPosDto);
        ChannelInboundHandlerAdapter handlerAdapter =
                new CreateMdIndexHandler(new MdIndex(),mdAttrPosDto, OpsTypeEnum.CREATE_FILE.getCode());
        IndexClient createMdClient = new IndexClient();
        createMdClient.connectAndHandle(handlerAdapter);
        System.out.println(mdAttrPosDto);
    }

    @Test
    public void testCreateFileMdAttr() {

    }
}
