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
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.create.view.ForceOption;
import net.sf.jsqlparser.statement.create.view.TemporaryOption;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.validation.ValidationCapability;
import net.sf.jsqlparser.util.validation.metadata.NamedObject;

/**
 * @author gitmotte
 */
public class CreateDeputyClassValidator extends AbstractValidator<CreateDeputyClass> {

    @Override
    public void validate(CreateDeputyClass createDeputyClass) {
        for (ValidationCapability c : getCapabilities()) {
            validateFeature(c, Feature.createSelectDeputy);
            validateFeature(c, createDeputyClass.isOrReplace(), Feature.createOrReplaceView);
            validateName(c, NamedObject.view, createDeputyClass.getDeputyClass().getFullyQualifiedName(), false);
        }
        SelectValidator v = getValidator(SelectValidator.class);
        Select select = createDeputyClass.getSelect();
        if (isNotEmpty(select.getWithItemsList())) {
            select.getWithItemsList().forEach(wi -> wi.accept(v));
        }
        select.getSelectBody().accept(v);

    }

}
