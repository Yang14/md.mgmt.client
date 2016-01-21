package md.mgmt.network.connect.create;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.ReqDto;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.recv.create.index.MdAttrPos;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class CreateMdIndexHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(CreateMdIndexHandler.class);

    private MdIndex mdIndex;
    private MdAttrPosDto mdAttrPosDto;
    private int opsType;

    public CreateMdIndexHandler(MdIndex mdIndex, MdAttrPosDto mdAttrPosDto, int opsType) {
        this.mdIndex = mdIndex;
        this.mdAttrPosDto = mdAttrPosDto;
        this.opsType = opsType;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(opsType);
        reqDto.setOpsContent(JSON.toJSONString(mdIndex));
        String sendStr = JSON.toJSONString(reqDto);
        ctx.writeAndFlush(sendStr);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RespDto respDto = JSON.parseObject((String) msg, RespDto.class);
        BeanUtils.copyProperties(respDto, mdAttrPosDto);
        mdAttrPosDto.setMdAttrPos(JSON.parseObject(respDto.getObjStr(), MdAttrPos.class));
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
