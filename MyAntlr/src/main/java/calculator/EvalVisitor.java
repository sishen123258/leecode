package calculator;

public class EvalVisitor extends ExprBaseVisitor<Integer>{

    @Override
    public Integer visitProg(ExprParser.ProgContext ctx) {
        return super.visitProg(ctx);
    }

    @Override
    public Integer visitStat(ExprParser.StatContext ctx) {
        return super.visitStat(ctx);
    }

    @Override
    public Integer visitExpr(ExprParser.ExprContext ctx) {
        return super.visitExpr(ctx);
    }
}
