package net.unibave.npa.core.persistence.abstracts;

import net.unibave.npa.core.persistence.model.DeploymentDescriptorBean;

import java.io.File;
import java.util.Map;

public interface IPersistenceContextFactory {

    AbstractPersistenceContext factory(final String contextKey, final Map<String, Object> properties) throws Exception;

    AbstractPersistenceContext factory(final String contextKey, final File propertiesFile) throws Exception;

    AbstractPersistenceContext factory(final String contextKey, final String filePath) throws Exception;

    AbstractPersistenceContext factory(final String contextKey) throws Exception;

    AbstractPersistenceContext factory(final String contextKey, final DeploymentDescriptorBean deploymentDescriptorBean) throws Exception;

}
