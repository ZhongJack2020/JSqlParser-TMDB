/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2023 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */

package net.sf.jsqlparser.statement.create.deputyclass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
//TODO4 TMDB
//createTJoinDeputyClass类，javacc解析之后可以生成一个这样的类，之后对这个类进行解析，具体写法参考createdeputyclass
//需要完成get set， 重写tostring
public class CreateTJoinDeputyClass implements Statement {

    private Table tJoinDeputyClass = null; //表格对象
    private Select selectFirst = null;  //第一个选择对象
//    private Select selectEnd = null;  //第二个选择对象
    private String type;


    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    public Table getTJoinDeputyClass() {
        return tJoinDeputyClass;
    }

    public void setTJoinDeputyClass(Table tJoinDeputyClass) {
        this.tJoinDeputyClass = tJoinDeputyClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public boolean isOrReplace() {
//        return orReplace;
//    }

//    /**
//     * @param orReplace was "OR REPLACE" specified?
//     */
//    public void setOrReplace(boolean orReplace) {
//        this.orReplace = orReplace;
//    }

    public Select getSelectFirst() {
        return selectFirst;
    }

//    public Select getSelectEnd() {
//        return selectEnd;
//    }

    public void setSelectFirst(Select selectFirst) {
        this.selectFirst = selectFirst;
    }

//    public void setSelectEnd(Select selectEnd) {
//        this.selectEnd = selectEnd;
//    }

    @Override
    public String toString() {
        //SQL语句:create TJoinDeputyClass xxx as select xxx intersect select xxx;
        StringBuilder sql = new StringBuilder("CREATE ");
//        if (isOrReplace()) {
//            sql.append("OR REPLACE ");
//        }
        sql.append(type+" ");
        sql.append(tJoinDeputyClass);
        sql.append(" AS ").append(selectFirst);
//        sql.append(" INTERSECT ").append(selectEnd);
        return sql.toString();
    }



}
