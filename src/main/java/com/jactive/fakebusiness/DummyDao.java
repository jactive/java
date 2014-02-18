package com.jactive.fakebusiness;

public interface DummyDao {
    String getMessageById(long id);

    void saveMessage(long id, String message);
}
