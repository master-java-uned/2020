package com.covid19.telegramservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.api.TLConfig;
import org.telegram.api.auth.TLAuthorization;
import org.telegram.api.auth.TLCheckedPhone;
import org.telegram.api.chat.channel.TLChannel;
import org.telegram.api.contacts.TLResolvedPeer;
import org.telegram.api.engine.*;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.api.functions.auth.TLRequestAuthCheckPhone;
import org.telegram.api.functions.channels.TLRequestChannelsCheckUsername;
import org.telegram.api.functions.channels.TLRequestChannelsJoinChannel;
import org.telegram.api.functions.contacts.TLRequestContactsResolveUsername;
import org.telegram.api.functions.help.TLRequestHelpGetConfig;
import org.telegram.api.functions.messages.TLRequestMessagesGetMessages;
import org.telegram.api.functions.messages.TLRequestMessagesImportChatInvite;
import org.telegram.api.input.chat.TLAbsInputChannel;
import org.telegram.api.input.user.TLInputUser;
import org.telegram.bot.kernel.IKernelComm;
import org.telegram.bot.kernel.KernelComm;
import org.telegram.api.input.chat.TLInputChannel;
import org.telegram.api.messages.TLMessagesChatFull;
import org.telegram.api.updates.TLAbsUpdates;
import org.telegram.bot.kernel.engine.MemoryApiState;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class TelegramServiceApplication {

    public static void main(String[] args) throws IOException, TimeoutException {
        SpringApplication.run(TelegramServiceApplication.class, args);
         MemoryApiState apiState = new MemoryApiState("test.log");

        TelegramApi api = new TelegramApi(apiState, new AppInfo(1316408, "console", "test", "1", "en"), new ApiCallback() {

            @Override
            public void onAuthCancelled(TelegramApi api) {
                System.err.println("onAuthCancelled");
            }

            @Override
            public void onUpdatesInvalidated(TelegramApi api) {
                System.err.println("onUpdatesInvalidated Big Error");
            }

            @Override
            public void onUpdate(TLAbsUpdates updates) {
                System.err.println("onUpdate");
            }
        });
        try {
            final TLConfig config = api.doRpcCallNonAuth(new TLRequestHelpGetConfig());
//            apiState.setPrimaryDc(config.getThisDc());
            apiState.updateSettings(config);
        } catch (IOException | TimeoutException e) {

        }

//        new Thread(() -> {
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//             //   Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            TLRequestAuthCheckPhone tl = new TLRequestAuthCheckPhone();
//            tl.setPhoneNumber("+34616048996");
//            try {
//                TLCheckedPhone t = api.doRpcCallNonAuth(tl);
//                System.err.println(t.isPhoneRegistered());
//            } catch (RpcException ex) {
////                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
////            } catch (TimeoutException ex) {
//////                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (java.util.concurrent.TimeoutException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        String phoneNumber = "+34616048996";
//        TLRequestAuthCheckPhone checkPhone = new TLRequestAuthCheckPhone();
//        checkPhone.setPhoneNumber(phoneNumber);
//        api.doRpcCall(checkPhone, new RpcCallbackEx<TLCheckedPhone>() {
//            public void onConfirmed() {
//                System.out.print("############################### onConfirmed");
//            }
//
//            public void onResult(TLCheckedPhone result) {
////                boolean invited = result.getPhoneInvited();
//                boolean registered = result.isPhoneRegistered();
//                System.out.print("############################### onResult" + registered);
//                // TODO process response further
//            }
//
//            public void onError(int errorCode, String message) {
//                System.out.print("############################### onError" + message);
//            }
//        });


// Call service synchronously
//        TLCheckedPhone checkedPhone =  api.doRpcCall(checkPhone);
//        TLAuthorization authorization = new TLAuthorization();
//        TLRequestChannelsCheckUsername = api.doRpcCall(authorization(1316408));
//        int invited = checkedPhone.getClassId();
//        boolean registered = checkedPhone.isPhoneRegistered();
        IKernelComm kernelComm = new KernelComm(1316408, apiState);
        kernelComm.getApi();
        String str="https://t.me/sanidadgob";
//        for (String str : links){
            if (str.contains("t.me/joinchat")){
                String hash = str.split("/")[(str.split("/").length)-1];
                TLRequestMessagesImportChatInvite in = new TLRequestMessagesImportChatInvite();
                in.setHash(hash);
                try {
                    TLAbsUpdates bb = kernelComm.getApi().doRpcCall(in);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (str.contains("t.me/")){
                String username = str.split("/")[(str.split("/").length)-1];
                try {
                    TLRequestContactsResolveUsername ru = new TLRequestContactsResolveUsername();
                    ru.setUsername(username);

                    TLResolvedPeer peer = kernelComm.getApi().doRpcCall(ru);
                    TLRequestChannelsJoinChannel join = new TLRequestChannelsJoinChannel();
                    TLInputChannel ch = new TLInputChannel();
//                    ch.setChannelId(peer.getChats().get(0).getId());
//                    ch.setAccessHash(((TLChannel) peer.getChats().get(0)).getAccessHash());
                    join.setChannel(ch);
                    kernelComm.getApi().doRpcCall(join);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//        }




    }

}
