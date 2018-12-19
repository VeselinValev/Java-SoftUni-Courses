import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String currentEnum = reader.readLine();
        Class cl = null;
        if (currentEnum.equals("Rank")){
            cl = CardRanks.class;
        }else{
            cl = CardSuits.class;
        }
        Annotation annotation = cl.getAnnotation(EnumInfo.class);
        EnumInfo enumInfo = (EnumInfo) annotation;
        System.out.printf("Type = %s, Description = %s%n", enumInfo.type(), enumInfo.description());
    }
}
