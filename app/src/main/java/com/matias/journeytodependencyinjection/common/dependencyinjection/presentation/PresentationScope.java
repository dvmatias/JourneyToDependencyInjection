package com.matias.journeytodependencyinjection.common.dependencyinjection.presentation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresentationScope {
}
