package app.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.List;

public class JSONparser {
    private Gson gson;

    public JSONparser() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T fromJSONtoObject(Class<T> tClass, String fileName) throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader(fileName));
        return this.gson.fromJson(jsonReader, tClass);
    }

    public <T> void fromObjectToJSON(List<T> objects, String file) throws IOException {
        FileWriter writer = new FileWriter(file);
        String content;
        if (objects.size() == 1){
            T object = objects.get(0);
            content = this.gson.toJson(object);
        }else{
            content = this.gson.toJson(objects);
        }

        writer.write(content);
        writer.close();
    }
}
