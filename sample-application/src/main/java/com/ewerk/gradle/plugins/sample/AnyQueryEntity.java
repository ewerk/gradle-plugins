package com.ewerk.gradle.plugins.sample;

import com.querydsl.core.annotations.QueryEntity;

/**
 * Uses the querydsl annotation processor
 * @author griffio
 * @since 1.0.3
 */
@QueryEntity
public class AnyQueryEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
