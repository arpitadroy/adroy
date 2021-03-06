/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.runtime.module.launcher.application;

import org.mule.runtime.module.launcher.artifact.DeployableArtifactWrapper;
import org.mule.runtime.module.launcher.descriptor.ApplicationDescriptor;
import org.mule.runtime.module.launcher.domain.Domain;

import java.io.IOException;

/**
 * Decorates the target deployer to properly switch out context classloader for deployment one where applicable. E.g. init() phase
 * may load custom classes for an application, which must be executed with deployment (app) classloader in the context, and not
 * Mule system classloader.
 */
public class ApplicationWrapper extends DeployableArtifactWrapper<Application, ApplicationDescriptor> implements Application {

  protected ApplicationWrapper(Application delegate) throws IOException {
    super(delegate);
  }

  public String getAppName() {
    return getArtifactName();
  }

  @Override
  public Domain getDomain() {
    return getDelegate().getDomain();
  }

  @Override
  public ApplicationStatus getStatus() {
    return getDelegate().getStatus();
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", getClass().getName(), getDelegate());
  }

  public Application getDelegate() {
    return super.getDelegate();
  }
}
