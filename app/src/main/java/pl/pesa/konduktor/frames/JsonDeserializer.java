package pl.pesa.konduktor.frames;

import com.google.gson.Gson;

public class JsonDeserializer {
    public Frame deserializeJsonToFrameObject(String content) {
        //TODO add try and not null
        Gson gson = new Gson();
        Frame frame = gson.fromJson(content, Frame.class);
        if (frame.getFrameType().equals(FrameTypes.LOGRESPONSE)) {
            LogResponseFrame logResponseFrame = gson.fromJson(content, LogResponseFrame.class);
            return logResponseFrame;
        } else {
            return frame;
        }


    }
}

