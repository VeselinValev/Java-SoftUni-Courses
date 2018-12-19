package exerciseOOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.instrument.IllegalClassFormatException;
import java.util.*;
import java.util.stream.Collectors;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfSongs = Integer.parseInt(reader.readLine());
        SongDatabase songDatabase = new SongDatabase();

        for (int i = 0; i < numberOfSongs; i++) {
            String[] tokens = reader.readLine().split(";");
            String artistName = tokens[0];
            String songName = tokens[1];
            String duration = tokens[2];
            try {
                Song song = new Song(artistName, songName, duration);
                songDatabase.addSong(song);
                System.out.println("Song added.");
            } catch (InvalidSongException ise) {
                System.out.println(ise.getMessage());
            }
        }
        System.out.println(songDatabase);
    }
}
