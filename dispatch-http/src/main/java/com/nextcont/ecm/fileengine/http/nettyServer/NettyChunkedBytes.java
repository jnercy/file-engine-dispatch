package com.nextcont.ecm.fileengine.http.nettyServer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.stream.ChunkedInput;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2016/10/8
 * Time: 10:19
 * To change this template use File | Settings | File Templates.
 */
public class NettyChunkedBytes implements ChunkedInput<ByteBuf> {

    private byte[] bytes;
    private int offset;
    private int len;
    private int chunkSize;

    public NettyChunkedBytes(byte[] bytes,int chunkSize){
        this.bytes = bytes;
        this.offset=0;
        this.len = bytes.length;
        this.chunkSize = chunkSize;
    }

    @Override
    public boolean isEndOfInput() throws Exception {
        return ! (offset < len);
    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public ByteBuf readChunk(ChannelHandlerContext ctx) throws Exception {
        int offset = this.offset;
        if (offset >= len) {
            return null;
        }

        int chunkSize = Math.min(this.chunkSize, len - offset);
        // Check if the buffer is backed by an byte array. If so we can optimize it a bit an safe a copy

        ByteBuf buf = ctx.alloc().heapBuffer(chunkSize);
        boolean release = true;
        try {
            buf.writeBytes(bytes,offset,chunkSize);
            buf.writerIndex(chunkSize);
            this.offset = offset + chunkSize;
            release = false;
            return buf;
        } finally {
            if (release) {
                buf.release();
            }
        }
    }

}
