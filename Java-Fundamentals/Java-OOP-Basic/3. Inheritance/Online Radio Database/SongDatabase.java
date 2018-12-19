package exerciseOOP;

public class SongDatabase {
    private int songCounter = 0;
    private long totalDuration = 0;

    public void addSong(Song song){
        this.songCounter++;
        this.totalDuration = this.totalDuration + song.getSeconds() + song.getMinutes() * 60;
    }

    @Override
    public String toString(){
        long totalTime = this.totalDuration;
        long hours = totalTime / 3600;
        totalTime %= 3600;
        long minutes = totalTime / 60;
        totalTime %= 60;
        long seconds = totalTime;

        String result = String.format("Songs added: %d\nPlaylist length: %dh %dm %ds",this.songCounter, hours, minutes, seconds);
        return result;
    }
}
