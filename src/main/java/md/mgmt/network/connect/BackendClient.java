package md.mgmt.network.connect;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * Created by Mr-yang on 16-1-9.
 */
public class BackendClient {
    private String HOST;
    private int PORT;

    public BackendClient(String HOST, int PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    public void connectAndHandle(final ChannelInboundHandlerAdapter handlerAdapter) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(
                                    new ObjectEncoder(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    handlerAdapter);
                        }
                    });
            b.connect(HOST, PORT).sync().channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
