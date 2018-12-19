package exerciseOOP;

public class Song {
    private String artistName;
    private String songName;
    private int minutes;
    private int seconds;

    public Song(String artistName, String songName, String duration) {
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.setDuration(duration);
    }

    public String getArtistName() {
        return artistName;
    }

    private void setArtistName(String artistName) {
        if(artistName == null || artistName.length() < 3 || artistName.length() > 20){
            throw new InvalidArtistNameException();
        }
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    private void setSongName(String songName) {
        if(songName == null || songName.length() < 3 || songName.length() > 30){
            throw new InvalidSongNameException();
        }
        this.songName = songName;
    }

    public int getMinutes() {
        return minutes;
    }

    private void setMinutes(int minutes) {
        if(minutes < 0 || minutes > 14){
            throw new InvalidSongMinutesException();
        }
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    private void setSeconds(int seconds) {
        if(seconds < 0 || seconds > 59){
            throw new InvalidSongSecondsException();
        }
        this.seconds = seconds;
    }

    private void setDuration(String duration){
        String[] durationTokens = duration.split(":");
        int minutes;
        int seconds;
        try{
            minutes = Integer.parseInt(durationTokens[0]);
            seconds = Integer.parseInt(durationTokens[1]);
        }catch (NumberFormatException nfe){
            throw new InvalidSongLengthException();
        }
        this.setMinutes(minutes);
        this.setSeconds(seconds);
    }
}
