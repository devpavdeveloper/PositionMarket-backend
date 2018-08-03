package by.psu.model.postgres.generator;

import by.psu.model.postgres.BasicEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.Objects;

public class CustomUUIDGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        if (Objects.isNull(object)) throw new HibernateException(new NullPointerException());
        return Objects.isNull(((BasicEntity) object).getId()) ? super.generate(session, object) : ((BasicEntity) object).getId();
    }
}
