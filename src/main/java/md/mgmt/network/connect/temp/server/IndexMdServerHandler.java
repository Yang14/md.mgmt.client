/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package md.mgmt.network.connect.temp.server;

import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import md.mgmt.base.md.ClusterNodeInfo;
import md.mgmt.base.md.ExactCode;
import md.mgmt.network.recv.create.index.MdAttrPos;
import md.mgmt.network.recv.create.index.MdAttrPosDto;

/**
 * Handles both client-side and server-side handler depending on which
 * constructor was called.
 */
public class IndexMdServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("s.channelRead:" + msg);
        MdAttrPosDto mdAttrPosDto = new MdAttrPosDto();
        mdAttrPosDto.setSuccess(true);
        mdAttrPosDto.setMsg("元数据索引创建成功");
        ClusterNodeInfo clusterNodeInfo = new ClusterNodeInfo();
        clusterNodeInfo.setIp("127.0.0.1");
        clusterNodeInfo.setPort(8008);
        clusterNodeInfo.setDistrCode("77777");
        ExactCode exactCode = new ExactCode();
        exactCode.setDistrCode("77777");
        exactCode.setFileCode("11111");
        MdAttrPos mdAttrPos = new MdAttrPos();
        mdAttrPos.setClusterNodeInfo(clusterNodeInfo);
        mdAttrPos.setExactCode(exactCode);
        mdAttrPosDto.setMdAttrPos(mdAttrPos);

        ctx.writeAndFlush(JSON.toJSONString(mdAttrPosDto));
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
