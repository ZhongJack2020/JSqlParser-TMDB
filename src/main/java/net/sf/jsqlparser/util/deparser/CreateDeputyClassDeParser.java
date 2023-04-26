/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.util.deparser;

import net.sf.jsqlparser.statement.create.deputyclass.CreateDeputyClass;
import net.sf.jsqlparser.statement.create.deputyclass.CreateDeputyClass;
import net.sf.jsqlparser.statement.create.deputyclass.CreateDeputyClass;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.WithItem;


public class CreateDeputyClassDeParser extends AbstractDeParser<CreateDeputyClass> {

    private final SelectVisitor selectVisitor;

    public CreateDeputyClassDeParser(StringBuilder buffer) {
        super(buffer);
        SelectDeParser selectDeParser = new SelectDeParser();
        selectDeParser.setBuffer(buffer);
        ExpressionDeParser expressionDeParser = new ExpressionDeParser(selectDeParser, buffer);
        selectDeParser.setExpressionVisitor(expressionDeParser);
        selectVisitor = selectDeParser;
    }

    public CreateDeputyClassDeParser(StringBuilder buffer, SelectVisitor selectVisitor) {
        super(buffer);
        this.selectVisitor = selectVisitor;
    }

    @Override
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.NPathComplexity"})
    public void deParse(CreateDeputyClass createDeputyClass) {
        buffer.append("CREATE ");
        if (createDeputyClass.isOrReplace()) {
            buffer.append("OR REPLACE ");
        }
        
        buffer.append(createDeputyClass.getType()+" ").append(createDeputyClass.getDeputyClass().getFullyQualifiedName());
       
        buffer.append(" AS ");

        Select select = createDeputyClass.getSelect();
        if (select.getWithItemsList() != null) {
            buffer.append("WITH ");
            boolean first = true;
            for (WithItem item : select.getWithItemsList()) {
                if (!first) {
                    buffer.append(", ");
                } else {
                    first = false;
                }

                item.accept(selectVisitor);
            }
            buffer.append(" ");
        }
        createDeputyClass.getSelect().getSelectBody().accept(selectVisitor);
    }

}
