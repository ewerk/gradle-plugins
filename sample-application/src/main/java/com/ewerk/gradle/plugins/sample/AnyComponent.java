package com.ewerk.gradle.plugins.sample;

import dagger.Component;

@Component(modules = AnyModule.class)
public interface AnyComponent {
   AnyThing thing();
}
