package com.ewerk.gradle.plugins.sample;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnyMorphiaEntity is a Querydsl query type for AnyMorphiaEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAnyMorphiaEntity extends EntityPathBase<AnyMorphiaEntity> {

    private static final long serialVersionUID = -549660779L;

    public static final QAnyMorphiaEntity anyMorphiaEntity = new QAnyMorphiaEntity("anyMorphiaEntity");

    public final SimplePath<Bean> bean = createSimple("bean", Bean.class);

    public final ListPath<Bean, SimplePath<Bean>> beans = this.<Bean, SimplePath<Bean>>createList("beans", Bean.class, SimplePath.class, PathInits.DIRECT2);

    public final ComparablePath<org.bson.types.ObjectId> id = createComparable("id", org.bson.types.ObjectId.class);

    public final StringPath name = createString("name");

    public final NumberPath<Double> salary = createNumber("salary", Double.class);

    public QAnyMorphiaEntity(String variable) {
        super(AnyMorphiaEntity.class, forVariable(variable));
    }

    public QAnyMorphiaEntity(Path<? extends AnyMorphiaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAnyMorphiaEntity(PathMetadata metadata) {
        super(AnyMorphiaEntity.class, metadata);
    }

}

