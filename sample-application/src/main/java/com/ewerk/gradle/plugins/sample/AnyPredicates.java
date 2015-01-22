package com.ewerk.gradle.plugins.sample;

import com.mysema.query.types.expr.BooleanExpression;

/**
 * @author griffio
 * @since 1.0.3
 */
public class AnyPredicates {

    public BooleanExpression findId(Long id) {
        return QAnyJpaEntity.anyJpaEntity.id.eq(id);
    }

}
