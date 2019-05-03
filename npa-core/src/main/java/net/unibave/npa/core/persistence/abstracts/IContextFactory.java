/**
 *
 */
package net.unibave.npa.core.persistence.abstracts;

/**
 * @author wesley
 */
@FunctionalInterface
public interface IContextFactory {

    AbstractPersistenceContext factory() throws Exception;

}
