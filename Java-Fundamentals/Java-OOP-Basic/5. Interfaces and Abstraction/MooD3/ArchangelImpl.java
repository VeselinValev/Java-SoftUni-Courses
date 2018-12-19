package MooD3;

public class ArchangelImpl extends CharacterImpl implements Archangel{
    private int mana;

    public ArchangelImpl(String userName, String type, int level, int mana) {
        super(userName, type, level);
        this.setMana(mana);
        this.setHashedPassword(userName);
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    @Override
    public void setHashedPassword(String userName) {
        StringBuilder firstComponent = new StringBuilder(userName).reverse();
        int secondComponent = this.getUserName().length() * 21;
        super.setHashedPassword(firstComponent.toString() + secondComponent);
    }

    @Override
    public int getMana() {
        return this.mana;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\"%s\" | \"%s\" -> %s", super.getUserName(), super.getHashedPassword(), super.getType()));
        sb.append("\n").append(super.getLevel()*this.getMana());
        return sb.toString();
    }
}
