package net.sf.jsqlparser.statement.create.duptyclass;

/**
 * file name: TJoinDeputyClassTest
 * Description:
 * This file is to ...
 * Creation Time: 2023/5/8 12:36
 *
 * @author ZhongJiChuan
 * @since JSqlParser 1.0
 */

import net.sf.jsqlparser.JSQLParserException;

import static net.sf.jsqlparser.test.TestUtils.assertSqlCanBeParsedAndDeparsed;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import org.junit.jupiter.api.Test;

public class TJoinDeputyClassTest {
    private CCJSqlParserManager parserManager = new CCJSqlParserManager();

    @Test
    public void testCreateTJoinDeputyClass() throws JSQLParserException {
        String statement = "CREATE TJoinDeputyClass tjoin23423 AS SELECT * FROM mobile_phone_traj INTERSECT SELECT * FROM watch_traj;";
//        String statement = "CREATE JOINDEPUTY test32861 AS SELECT * FROM mobile_phone_traj";
//        String statement = "INSERT INTO mytable (col1, col2) VALUES (a, b), (d, e)";
//        String statement = "CREATE VIEW view1 AS (SELECT a, b FROM testtab) UNION (SELECT b, c FROM testtab2)";
        assertSqlCanBeParsedAndDeparsed(statement);
    }

}
