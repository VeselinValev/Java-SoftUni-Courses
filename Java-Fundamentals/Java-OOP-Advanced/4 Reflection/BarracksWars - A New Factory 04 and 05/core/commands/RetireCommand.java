package core.commands;

public class RetireCommand extends Command{

    @Override
    public String execute() {
        String result = null;
        try{
            super.getRepository().removeUnit(super.getData()[1]);
        }catch (IllegalStateException ise){
            result =  ise.getMessage();
        }
        return result;
    }
}
