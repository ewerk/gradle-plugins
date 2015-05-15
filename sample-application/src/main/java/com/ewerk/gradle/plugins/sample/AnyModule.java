package com.ewerk.gradle.plugins.sample;

import dagger.Module;
import dagger.Provides;

@Module
public class AnyModule {
  @Provides
  AnyThing provideAnyThing() {
    return new AnyThing();
  }
}