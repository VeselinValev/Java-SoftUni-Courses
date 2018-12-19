package factories;

import contracts.Gem;
import entities.gems.Amethyst;
import entities.gems.Emerald;
import entities.gems.Ruby;

public class GemFactory {
    public static Gem createGem(String gemType){
        switch (gemType) {
            case "RUBY":
                return new Ruby();
            case "EMERALD":
                return new Emerald();
            case "AMETHYST":
                return new Amethyst();
        }
         return null;
    }
}
