package MooD3;

public interface Character {
    String getUserName();
    String getType();
    String getHashedPassword();
    int getLevel();
    void setHashedPassword(String userName);
}
