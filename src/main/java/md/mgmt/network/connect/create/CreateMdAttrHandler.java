package md.mgmt.network.connect.create;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.facade.resp.CreateMdResp;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.md.ExactCode;
import md.mgmt.base.md.MdAttr;
import md.mgmt.base.ops.ReqDto;
import md.mgmt.network.send.PutMdAttrDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class CreateMdAttrHandler extends ChannelInboundHandlerAdapter {

    private MdAttr mdAttr;
    private ExactCode exactCode;
    private CreateMdResp createMdResp;

    public CreateMdAttrHandler(MdAttr mdAttr, ExactCode exactCode, CreateMdResp createMdResp) {
        this.mdAttr = mdAttr;
        this.exactCode = exactCode;
        this.createMdResp = createMdResp;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        PutMdAttrDto putMdAttrDto = new PutMdAttrDto();
        putMdAttrDto.setExactCode(exactCode);
        putMdAttrDto.setMdAttr(mdAttr);
        reqDto.setOpsType(OpsTypeEnum.CREATE_FILE.getCode());
        reqDto.setOpsContent(JSON.toJSONString(putMdAttrDto));
        ctx.writeAndFlush(JSON.toJSONString(reqDto));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        CreateMdResp temp = JSON.parseObject((String) msg, CreateMdResp.class);
        BeanUtils.copyProperties(temp, createMdResp);
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
