package com.example.viewflipper_cricleindicator;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {
    private boolean success;
    private String message;
    private List<ImagesSlider> result;

    public List<ImagesSlider> getResult() {
        return result;
    }
}

