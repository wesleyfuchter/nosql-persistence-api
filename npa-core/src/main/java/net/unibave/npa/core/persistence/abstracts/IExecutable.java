package net.unibave.npa.core.persistence.abstracts;

@FunctionalInterface
public interface IExecutable<T, E> {

    T execute(E e) throws Exception;

}
