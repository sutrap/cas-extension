/*
 * File created on Dec 15, 2014 
 *
 * Copyright (c) 2015 Carl Harris, Jr.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.soulwing.cas.extension;

import java.util.List;

import org.jboss.as.controller.AbstractBoottimeAddStepHandler;
import org.jboss.as.controller.AbstractRemoveStepHandler;
import org.jboss.as.controller.OperationContext;
import org.jboss.as.controller.OperationFailedException;
import org.jboss.as.controller.SimpleResourceDefinition;
import org.jboss.as.controller.operations.common.GenericSubsystemDescribeHandler;
import org.jboss.as.controller.registry.ManagementResourceRegistration;
import org.jboss.dmr.ModelNode;
import org.jboss.msc.service.ServiceController;
import org.soulwing.cas.deployment.DependenciesDeploymentProcessor;
import org.soulwing.cas.deployment.DescriptorDeploymentProcessor;
import org.soulwing.cas.deployment.ServletExtensionDeploymentProcessor;


/**
 * A resource definition for the CAS subsystem.
 *
 * @author Carl Harris
 */
public class SubsystemDefinition extends SimpleResourceDefinition {
  public static final SubsystemDefinition INSTANCE =
      new SubsystemDefinition();

  private SubsystemDefinition() {
    super(Paths.SUBSYSTEM, 
        ResourceUtil.getResolver(),
        SubsystemAdd.INSTANCE, SubsystemRemove.INSTANCE);
  }

  @Override
  public void registerOperations(
      ManagementResourceRegistration resourceRegistration) {
    super.registerOperations(resourceRegistration);
    resourceRegistration.registerOperationHandler(
        GenericSubsystemDescribeHandler.DEFINITION,
        GenericSubsystemDescribeHandler.INSTANCE);
  }

  @Override
  public void registerAttributes(
      ManagementResourceRegistration resourceRegistration) {
  }

  static class SubsystemAdd extends AbstractBoottimeAddStepHandler {

    static final SubsystemAdd INSTANCE = new SubsystemAdd();

    private SubsystemAdd() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void populateModel(ModelNode operation, ModelNode model)
        throws OperationFailedException {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performBoottime(OperationContext context, ModelNode operation,
        ModelNode model)
        throws OperationFailedException {

      DescriptorDeploymentProcessor.addStepHandler(context);
      DependenciesDeploymentProcessor.addStepHandler(context);
      ServletExtensionDeploymentProcessor.addStepHandler(context);
    }
    
  }
  
  static class SubsystemRemove extends AbstractRemoveStepHandler {

    static final SubsystemRemove INSTANCE = new SubsystemRemove();

    private SubsystemRemove() {
    }

    @Override
    protected void performRuntime(OperationContext context, 
        ModelNode operation, ModelNode model) throws OperationFailedException {
        //Remove any services installed by the corresponding add handler here
        //context.removeService(ServiceName.of("some", "name"));
    }

  }

}
