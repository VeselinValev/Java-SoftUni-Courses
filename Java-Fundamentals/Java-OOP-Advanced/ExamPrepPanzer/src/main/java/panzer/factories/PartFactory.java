package panzer.factories;

import panzer.contracts.Part;
import panzer.models.parts.ArsenalPart;
import panzer.models.parts.EndurancePart;
import panzer.models.parts.ShellPart;

import java.math.BigDecimal;
import java.util.List;

public class PartFactory {

    public static Part createArsenalPart(List<String> data){
        return new ArsenalPart(data.get(3), Double.parseDouble(data.get(4))
                , BigDecimal.valueOf(Long.parseLong(data.get(5))), Integer.parseInt(data.get(6)));
    }

    public static Part createShellPart(List<String> data){
        return new ShellPart(data.get(3), Double.parseDouble(data.get(4))
                , BigDecimal.valueOf(Long.parseLong(data.get(5))), Integer.parseInt(data.get(6)));
    }

    public static Part createEndurancePart(List<String> data){
        return new EndurancePart(data.get(3), Double.parseDouble(data.get(4))
                , BigDecimal.valueOf(Long.parseLong(data.get(5))), Integer.parseInt(data.get(6)));
    }
}
