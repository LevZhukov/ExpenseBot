package org.example.service.callbackCreator;

import org.springframework.stereotype.Component;

@Component
public class CallbackFromStringBasic implements CallbackFromString {
    public CallbackData stringToCallback(String callbackText){
        return new CallbackData(callbackText);
    }
}
