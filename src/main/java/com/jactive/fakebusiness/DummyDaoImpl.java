package com.jactive.fakebusiness;

public class DummyDaoImpl implements DummyDao {

    public String getMessageById(long id) {
        throw new UnsupportedOperationException("getMessageById is NOT implement yet.");
    }

    public void saveMessage(long id, String message) {
        throw new UnsupportedOperationException("saveMessage is NOT implement yet.");

    }
}
