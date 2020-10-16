// Generated from C:/Users/TYue1/IdeaProjects/LeeCode/leecode/MyAntlr/src/main/java/hypersrm\Command.g4 by ANTLR 4.8
package hypersrm;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CommandParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CommandVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CommandParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStat(CommandParser.StatContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandParser#rm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRm(CommandParser.RmContext ctx);
	/**
	 * Visit a parse tree produced by {@link CommandParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(CommandParser.PathContext ctx);
	/**
	 * Visit a parse tree produced by the {@code r}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(CommandParser.RContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rf}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRf(CommandParser.RfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code f}
	 * labeled alternative in {@link CommandParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitF(CommandParser.FContext ctx);
}