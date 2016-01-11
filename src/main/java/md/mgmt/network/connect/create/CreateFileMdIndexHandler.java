package md.mgmt.network.connect.create;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.network.recv.create.index.MdAttrPosDto;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.md.MdIndex;
import md.mgmt.base.ops.ReqDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class CreateFileMdIndexHandler extends ChannelInboundHandlerAdapter {

    private MdIndex mdIndex;
    private MdAttrPosDto mdAttrPosDto;

    public CreateFileMdIndexHandler(MdIndex mdIndex, MdAttrPosDto mdAttrPosDto) {
        this.mdIndex = mdIndex;
        this.mdAttrPosDto = mdAttrPosDto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(OpsTypeEnum.CREATE_FILE.getCode());
        reqDto.setOpsContent(JSON.toJSONString(mdIndex));
        ctx.writeAndFlush(JSON.toJSONString(reqDto));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        MdAttrPosDto temp = JSON.parseObject((String) msg, MdAttrPosDto.class);
        BeanUtils.copyProperties(temp, mdAttrPosDto);
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
