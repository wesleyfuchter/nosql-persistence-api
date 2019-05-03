package net.unibave.npa.core.persistence.abstracts;

import java.util.List;

public interface ICrud<K, E> {

    E create(E e) throws Exception;

    E read(K k) throws Exception;

    List<E> read() throws Exception;

    E update(E e) throws Exception;

    E delete(E e) throws Exception;

}
