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
public class FindFileMdAttrHandler extends ChannelInboundHandlerAdapter {

    private String fileCode;
    private RespDto respDto;

    public FindFileMdAttrHandler(String fileCode, RespDto respDto) {
        this.fileCode = fileCode;
        this.respDto = respDto;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ReqDto reqDto = new ReqDto();
        reqDto.setOpsType(OpsTypeEnum.FIND_FILE.getCode());
        reqDto.setOpsContent(fileCode);
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
