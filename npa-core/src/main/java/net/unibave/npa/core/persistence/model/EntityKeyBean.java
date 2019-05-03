package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.enumeration.KeyType;

import java.io.Serializable;

/**
 * @author wesley
 */
public class EntityKeyBean implements Serializable {

    private AttributeBean attribute;
    private KeyType keyType;
    private IKeyGenerator<?, ?> iKeyGenerator;
    private EntityBean entityBean;

    protected EntityKeyBean() {
        super();
    }

    protected EntityKeyBean(AttributeBean attribute, KeyType keyType, IKeyGenerator<?, ?> iKeyGenerator, EntityBean entityBean) {
        this.attribute = attribute;
        this.keyType = keyType;
        this.iKeyGenerator = iKeyGenerator;
        this.entityBean = entityBean;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
        result = prime * result + ((iKeyGenerator == null) ? 0 : iKeyGenerator.hashCode());
        result = prime * result + ((keyType == null) ? 0 : keyType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EntityKeyBean)) {
            return false;
        }
        EntityKeyBean other = (EntityKeyBean) obj;
        if (attribute == null) {
            if (other.attribute != null) {
                return false;
            }
        } else if (!attribute.equals(other.attribute)) {
            return false;
        }
        if (iKeyGenerator == null) {
            if (other.iKeyGenerator != null) {
                return false;
            }
        } else if (!iKeyGenerator.equals(other.iKeyGenerator)) {
            return false;
        }
        if (keyType != other.keyType) {
            return false;
        }
        return true;
    }

    public AttributeBean getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeBean attribute) {
        this.attribute = attribute;
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
    }

    public IKeyGenerator<?, ?> getKeyGenerator() {
        return iKeyGenerator;
    }

    public void setKeyGenerator(IKeyGenerator<?, ?> keyGenerator) {
        this.iKeyGenerator = keyGenerator;
    }

    public EntityBean getEntityBean() {
        return entityBean;
    }

    public void setEntityBean(EntityBean entityBean) {
        this.entityBean = entityBean;
    }

    public IKeyGenerator<?, ?> getiKeyGenerator() {
        return iKeyGenerator;
    }

    public void setiKeyGenerator(IKeyGenerator<?, ?> iKeyGenerator) {
        this.iKeyGenerator = iKeyGenerator;
    }

    private static final long serialVersionUID = -3894568822777202306L;

    public void setValue(Object value) throws Exception {
        attribute.setValue(value);
    }

    public Object getValue() throws Exception {
        return attribute.getValue();
    }
}
