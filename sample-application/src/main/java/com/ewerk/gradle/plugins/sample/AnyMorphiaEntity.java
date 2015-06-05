package com.ewerk.gradle.plugins.sample;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

import java.util.List;

@Entity("employees")
@Indexes(@Index(value = "salary", fields = @Field("salary")))
public class AnyMorphiaEntity {
  @Id
  private ObjectId id;
  private String name;
  @Reference
  private Bean bean;
  @Reference
  private List<Bean> beans;
  @Property("wage")
  private Double salary;
}
