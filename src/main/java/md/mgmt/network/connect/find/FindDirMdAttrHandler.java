package md.mgmt.network.connect.find;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.constant.OpsTypeEnum;
import md.mgmt.base.ops.ReqDto;
import md.mgmt.base.ops.RespDto;
import org.springframework.beans.BeanUtils;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class FindDirMdAttrHandler extends ChannelInboundHandlerAdapter {

    private Long distrCode;
    private RespDto respDto;

    public FindDirMdAttrHandler(Long distrCode, RespDto respDto) {
        this.distrCode = distrCode;
        this.respDto = respDto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(OpsTypeEnum.LIST_DIR.getCode());
        reqDto.setOpsContent(distrCode + "");
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
