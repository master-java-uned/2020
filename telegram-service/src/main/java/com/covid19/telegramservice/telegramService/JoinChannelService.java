package com.covid19.telegramservice.telegramService;

import org.telegram.api.engine.ApiCallback;
import org.telegram.api.engine.AppInfo;
import org.telegram.api.engine.TelegramApi;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.api.*;
import org.telegram.api.functions.channels.TLRequestChannelsJoinChannel;

public class JoinChannelService extends TelegramApi {

    public JoinChannelService(AbsApiState state, AppInfo _appInfo, ApiCallback _apiCallback) {
        super(state, _appInfo, _apiCallback);
    }
    TLRequestChannelsJoinChannel channel;
}
