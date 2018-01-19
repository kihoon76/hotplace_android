package com.hotplace25.mobile.utils;

import android.os.Handler;
import android.os.Message;

import com.hotplace25.mobile.interfaces.Command;

/**
 * Created by khnam on 2018-01-19.
 */

public class CommandHandler {
    private Exception occurredException;

    public boolean hasException() {
        return occurredException != null;
    }

    public void setOccuredException(Exception e) {
        occurredException = e;
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if(msg.obj instanceof Command) {
                ((Command)msg.obj).execute();
            }
        }
    };

    public String getExceptionMsg() {
        if(occurredException == null) return "";

        return occurredException.getMessage();
    }

    public void send(Command command) {
        Message message = handler.obtainMessage();
        message.obj = command;
        handler.sendMessage(message);
    }

    public void sendDelay(Command command, long delayMillis) {
        Message message = handler.obtainMessage();
        message.obj = command;
        handler.sendMessageDelayed(message, delayMillis);
    }
}
