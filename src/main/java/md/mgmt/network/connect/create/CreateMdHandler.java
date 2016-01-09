package md.mgmt.network.connect.create;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.ops.ReqDto;
import md.mgmt.facade.req.Md;
import md.mgmt.facade.resp.CreateMdResp;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class CreateMdHandler extends ChannelInboundHandlerAdapter {

    private Md md;
    private CreateMdResp createMdResp;

    public CreateMdHandler(Md md, CreateMdResp createMdResp) {
        this.md = md;
        this.createMdResp = createMdResp;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(OpsTypeEnum.CREATE_FILE.getCode());
        reqDto.setOpsContent(JSON.toJSONString(md.getMdIndex()));
        ctx.writeAndFlush(JSON.toJSONString(reqDto));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        CreateMdResp createMdResp1 = JSON.parseObject((String) msg, CreateMdResp.class);
        System.out.println("chRead: " + createMdResp1 );
        BeanUtils.copyProperties(createMdResp1, createMdResp);
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
