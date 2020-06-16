package com.covid19.telegramservice.telegramService;


import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.messages.TLAbsMessages;
import org.telegram.api.messages.TLMessagesChatFull;
import org.telegram.tl.*;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class TLRequestMessagesGetMessages extends TLMethod {

    /**
     * The constant CLASS_ID.
     */
    public static final int CLASS_ID = 0x8736a09;

    private TLAbsInputChannel channel;

    /**
     * Instantiates a new TL request channel get messages
     */
    public TLRequestMessagesGetMessages() {
        super();
    }

    public int getClassId() {
        return CLASS_ID;
    }

    public TLMessagesChatFull deserializeResponse(InputStream stream, TLContext context)
            throws IOException {
        final TLObject res = StreamingUtils.readTLObject(stream, context);
        if (res == null) {
            throw new IOException("Unable to parse response");
        }
        if ((res instanceof TLMessagesChatFull)) {
            return (TLMessagesChatFull) res;
        }
        throw new IOException("Incorrect response type. Expected " + TLMessagesChatFull.class.getName() +", got: " + res.getClass().getName());
    }

    public TLAbsInputChannel getChannel() {
        return channel;
    }

    public void setChannel(TLAbsInputChannel channel) {
        this.channel = channel;
    }

    public void serializeBody(OutputStream stream)
            throws IOException {
        StreamingUtils.writeTLObject(this.channel, stream);
    }

    public void deserializeBody(InputStream stream, TLContext context)
            throws IOException {
        this.channel = (TLAbsInputChannel) StreamingUtils.readTLObject(stream, context);
    }

    public String toString() {
        return "functions.channels.TLRequestChannelsGetFullChannel#8736a09";
    }

    
}
