/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.util.validation.validator;

import net.sf.jsqlparser.parser.feature.Feature;
import net.sf.jsqlparser.statement.create.deputyclass.CreateDeputyClass;
import net.sf.jsqlparser.statement.create.deputyclass.CreateTJoinDeputyClass;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.validation.ValidationCapability;
import net.sf.jsqlparser.util.validation.metadata.NamedObject;

/**
 * @author gitmotte
 */
//TODO9 TMDB
//实现CreateTJoinDeputyClassValidator类，这个类检验创建的CreateTJoinDeputyClass是否符合规则，具体参考CreateDeputyClassValidator
public class CreateTJoinDeputyClassValidator extends AbstractValidator<CreateTJoinDeputyClass> {
    @Override
    public void validate(CreateTJoinDeputyClass createTJoinDeputyClass) {
        for (ValidationCapability c : getCapabilities()) {
            validateFeature(c, Feature.createTJoinDeputyClass);//TODO JACk ???
//            validateFeature(c, createTJoinDeputyClass.isOrReplace(), Feature.createOrReplaceView);
            validateName(c, NamedObject.view, createTJoinDeputyClass.getTJoinDeputyClass().getFullyQualifiedName(), false);
        }
        SelectValidator v = getValidator(SelectValidator.class);
        Select selectFirst = createTJoinDeputyClass.getSelectFirst();
        Select selectEnd = createTJoinDeputyClass.getSelectEnd();
        if (isNotEmpty(selectFirst.getWithItemsList())) {
            selectFirst.getWithItemsList().forEach(wi -> wi.accept(v));
        }
        selectFirst.getSelectBody().accept(v);

        if (isNotEmpty(selectEnd.getWithItemsList())) {
            selectEnd.getWithItemsList().forEach(wi -> wi.accept(v));
        }
        selectEnd.getSelectBody().accept(v);

    }

}
