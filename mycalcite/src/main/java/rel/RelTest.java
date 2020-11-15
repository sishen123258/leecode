package rel;

import org.apache.calcite.plan.RelOptUtil;
import org.apache.calcite.plan.RelTraitDef;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.core.JoinRelType;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Programs;
import org.apache.calcite.tools.RelBuilder;

import java.util.List;

public class RelTest {


    private final boolean verbose;

    public RelTest(boolean verbose) {
        this.verbose = verbose;
    }

    public static void main(String[] args) {
        new RelTest(true).runAllExamples();
    }



    public void runAllExamples() {
        // Create a builder. The config contains a schema mapped
        // to the SCOTT database, with tables EMP and DEPT.
        final FrameworkConfig config = config().build();
        final RelBuilder builder = RelBuilder.create(config);
        for (int i = 0; i < 4; i++) {
            doExample(builder, i);
            final RelNode node = builder.build();
            if (verbose) {
                System.out.println(RelOptUtil.toString(node));
            }
        }
    }

    private RelBuilder doExample(RelBuilder builder, int i) {
        switch (i) {
            case 0:
                return example0(builder);
            case 1:
                return example1(builder);
            case 2:
                return example2(builder);
            case 3:
                return example3(builder);
            case 4:
                return example4(builder);
            default:
                throw new AssertionError("unknown example " + i);
        }
    }

    /**
     * Creates a relational expression for a table scan.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT *
     * FROM emp</pre></blockquote>
     */
    private RelBuilder example0(RelBuilder builder) {
        return builder
                .values(new String[] {"a", "b"}, 1, true, null, false);
    }

    /**
     * Creates a relational expression for a table scan.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT *
     * FROM emp</pre></blockquote>
     */
    private RelBuilder example1(RelBuilder builder) {
        return builder
                .scan("EMP");
    }

    /**
     * Creates a relational expression for a table scan and project.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT deptno, ename
     * FROM emp</pre></blockquote>
     */
    private RelBuilder example2(RelBuilder builder) {
        return builder
                .scan("EMP")
                .project(builder.field("DEPTNO"), builder.field("ENAME"));
    }

    /**
     * Creates a relational expression for a table scan, aggregate, filter.
     * It is equivalent to
     *
     * <blockquote><pre>SELECT deptno, count(*) AS c, sum(sal) AS s
     * FROM emp
     * GROUP BY deptno
     * HAVING count(*) &gt; 10</pre></blockquote>
     */
    private RelBuilder example3(RelBuilder builder) {
        return builder
                .scan("EMP")
                .aggregate(builder.groupKey("DEPTNO"),
                        builder.count(false, "C"),
                        builder.sum(false, "S", builder.field("SAL")))
                .filter(
                        builder.call(SqlStdOperatorTable.GREATER_THAN, builder.field("C"),
                                builder.literal(10)));
    }

    /**
     * Sometimes the stack becomes so deeply nested it gets confusing. To keep
     * things straight, you can remove expressions from the stack. For example,
     * here we are building a bushy join:
     *
     * <blockquote><pre>
     *                join
     *              /      \
     *         join          join
     *       /      \      /      \
     * CUSTOMERS ORDERS LINE_ITEMS PRODUCTS
     * </pre></blockquote>
     *
     * <p>We build it in three stages. Store the intermediate results in variables
     * `left` and `right`, and use `push()` to put them back on the stack when it
     * is time to create the final `Join`.
     */
    private RelBuilder example4(RelBuilder builder) {
        final RelNode left = builder
                .scan("CUSTOMERS")
                .scan("ORDERS")
                .join(JoinRelType.INNER, "ORDER_ID")
                .build();

        final RelNode right = builder
                .scan("LINE_ITEMS")
                .scan("PRODUCTS")
                .join(JoinRelType.INNER, "PRODUCT_ID")
                .build();

        return builder
                .push(left)
                .push(right)
                .join(JoinRelType.INNER, "ORDER_ID");
    }


    public static Frameworks.ConfigBuilder config() {
        final SchemaPlus rootSchema = Frameworks.createRootSchema(true);
        return Frameworks.newConfigBuilder()
                .parserConfig(SqlParser.Config.DEFAULT)
//                SqlParser.defaultSchema(
//                        CalciteAssert.addSchema(rootSchema, CalciteAssert.SchemaSpec.SCOTT_WITH_TEMPORAL))
                .traitDefs((List<RelTraitDef>) null);
//                .programs(Programs.heuristicJoinOrder(Programs.RULE_SET, true, 2));
    }

    /*public static void main(String[] args) {

        FrameworkConfig config= Frameworks.newConfigBuilder().build();

        RelBuilder builder=RelBuilder.create(config);

        RelNode node = builder.scan("EMP").build();

        String plan = RelOptUtil.toString(node);

        System.out.println("plan = " + plan);


    }*/


}
