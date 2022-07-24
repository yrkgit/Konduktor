package pl.pesa.konduktor.packet;

import com.google.gson.Gson;

public class JsonSerializer {

    public String crateJson(Serializable frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        System.out.println(frameString);
        return frameString;
    }
}
