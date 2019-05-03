package net.unibave.npa.core.persistence.abstracts;

/**
 * Created by wesley on 20/09/16.
 */
public interface IConverter {

    Object setAs(Object value);

    Object getAs(Object value);

}
