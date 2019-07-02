package by.psu.model.factory;

import by.psu.model.postgres.Language;
import by.psu.model.postgres.Nsi;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.UUID;

public abstract class FactoryNsi<T extends Nsi> {


    @Autowired
    private FactoryTranslate factoryTranslate;
    private Class<T> type;


    public FactoryNsi() {
        type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }


    public T create() {
        T obj = null;
        try {
            obj = type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public T create(String valueRu, String valueEn) {
        T nsi = create();
        nsi.setTitle( factoryTranslate.create(valueRu, valueEn) );
        return nsi;
    }

    public T create(UUID uuid, String valueRu, String valueEn) {
        T nsi = create();
        nsi.setId(uuid);
        nsi.setTitle( factoryTranslate.create(valueRu, valueEn) );
        return nsi;
    }


    public Nsi create(String valueRu, Language language) {
        T nsi = create();
        nsi.setTitle( factoryTranslate.create(valueRu, language) );
        return nsi;
    }

}
