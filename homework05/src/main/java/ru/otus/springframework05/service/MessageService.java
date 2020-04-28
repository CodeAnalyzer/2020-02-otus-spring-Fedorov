package ru.otus.springframework05.service;

import org.springframework.lang.Nullable;

public interface MessageService {
    void messagePrintOut(String messageID);
    void messagePrintOut(String messageID, String message);
    void messagePrintOut(String messageID, @Nullable Object[] objects);
    String getMessage (String messageID);
}
