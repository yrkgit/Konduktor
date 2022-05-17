package pl.pesa.konduktor.frames;

import com.google.gson.Gson;

public class JsonSerializer {

    //TODO po dodaniu builderow błędnie serializuje bez 2 z 3 zmiennych z frame

    public String crateJson(Serializable frame){
        Gson gson = new Gson();
        String frameString = gson.toJson(frame);
        System.out.println(frameString);
        return frameString;
    }
}
