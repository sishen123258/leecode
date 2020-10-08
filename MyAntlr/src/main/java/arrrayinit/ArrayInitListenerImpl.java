package arrrayinit;

public class ArrayInitListenerImpl extends ArrayInitBaseListener{

    @Override
    public void enterInit(ArrayInitParser.InitContext ctx) {
        System.out.println("\"");
    }

    @Override
    public void exitInit(ArrayInitParser.InitContext ctx) {
        System.out.println("\"");
    }

    @Override
    public void enterValue(ArrayInitParser.ValueContext ctx) {
        String text = ctx.INT().getText();
        System.out.printf("\\u%04x",Integer.valueOf(text));
    }

    @Override
    public void exitValue(ArrayInitParser.ValueContext ctx) {
        super.exitValue(ctx);
    }
}
