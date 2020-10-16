package hypersrm;


public class RmListener extends CommandBaseListener{

    private StringBuilder sb=new StringBuilder();
    private String dir;



    public RmListener(String dir) {
        this.dir=dir;
    }

    @Override
    public void enterRm(CommandParser.RmContext ctx) {
        sb.append("mv");
    }

    @Override
    public void exitRm(CommandParser.RmContext ctx) {
        sb.append(" ");
    }

    @Override
    public void enterPath(CommandParser.PathContext ctx) {
        sb.append(ctx.getText());
    }

    @Override
    public void exitPath(CommandParser.PathContext ctx) {
        sb.append(" ");
        sb.append(dir);
    }

    @Override
    public void enterR(CommandParser.RContext ctx) {
        sb.append("-r");
    }

    @Override
    public void exitR(CommandParser.RContext ctx) {
        sb.append(" ");
    }

    @Override
    public void enterRf(CommandParser.RfContext ctx) {
        sb.append("-r");
    }

    @Override
    public void exitRf(CommandParser.RfContext ctx) {
        sb.append(" ");
    }

    @Override
    public void enterF(CommandParser.FContext ctx) {
        sb.append("-r");
    }

    @Override
    public void exitF(CommandParser.FContext ctx) {
        sb.append(" ");
    }

    public String mvCommand(){
        return sb.toString();
    }
}
