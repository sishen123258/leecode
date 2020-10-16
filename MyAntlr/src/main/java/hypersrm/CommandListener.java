// Generated from C:/Users/TYue1/IdeaProjects/LeeCode/leecode/MyAntlr/src/main/java/hypersrm\Command.g4 by ANTLR 4.8
package hypersrm;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CommandParser}.
 */
public interface CommandListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CommandParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(CommandParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(CommandParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandParser#rm}.
	 * @param ctx the parse tree
	 */
	void enterRm(CommandParser.RmContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandParser#rm}.
	 * @param ctx the parse tree
	 */
	void exitRm(CommandParser.RmContext ctx);
	/**
	 * Enter a parse tree produced by {@link CommandParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(CommandParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link CommandParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(CommandParser.PathContext ctx);
	/**
	 * Enter a parse tree produced by the {@code r}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterR(CommandParser.RContext ctx);
	/**
	 * Exit a parse tree produced by the {@code r}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitR(CommandParser.RContext ctx);
	/**
	 * Enter a parse tree produced by the {@code rf}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRf(CommandParser.RfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rf}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRf(CommandParser.RfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code f}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterF(CommandParser.FContext ctx);
	/**
	 * Exit a parse tree produced by the {@code f}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitF(CommandParser.FContext ctx);
}