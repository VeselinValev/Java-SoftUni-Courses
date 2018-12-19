package MooD3;

public abstract class CharacterImpl implements Character{
    private String userName;
    private String type;
    private String hashedPassword;
    private int level;

    public CharacterImpl(String userName, String type, int level) {
        this.setUserName(userName);
        this.setType(type);
        this.setLevel(level);
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    private void setType(String type) {
        this.type = type;
    }

    public void setHashedPassword(String password) {
        this.hashedPassword = password;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public int getLevel() {
        return this.level;
    }
}
