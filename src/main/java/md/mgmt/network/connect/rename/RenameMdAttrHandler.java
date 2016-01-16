package md.mgmt.network.connect.rename;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.ops.ReqDto;
import md.mgmt.base.ops.RespDto;
import md.mgmt.network.recv.rename.RenameMdAttrDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class RenameMdAttrHandler extends ChannelInboundHandlerAdapter {

    private RenameMdAttrDto renameMdAttrDto;
    private RespDto respDto;

    public RenameMdAttrHandler(RenameMdAttrDto renameMdAttrDto, RespDto respDto) {
        this.renameMdAttrDto = renameMdAttrDto;
        this.respDto = respDto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(OpsTypeEnum.RENAME_FILE.getCode());
        reqDto.setOpsContent(JSON.toJSONString(renameMdAttrDto));
        ctx.writeAndFlush(JSON.toJSONString(reqDto));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        RespDto temp = JSON.parseObject((String) msg, RespDto.class);
        BeanUtils.copyProperties(temp, respDto);
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
