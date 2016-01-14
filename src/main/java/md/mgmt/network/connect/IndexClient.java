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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Mr-yang on 16-1-9.
 */
@Component
public class IndexClient {
    private static final Logger logger = LoggerFactory.getLogger(IndexClient.class);

    private static final String HOST = System.getProperty("host", "192.168.0.13");
    private static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

    public void connectAndHandle(final ChannelInboundHandlerAdapter handlerAdapter) {
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
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        } finally {
            group.shutdownGracefully();
        }
    }
}
