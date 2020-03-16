package unicorn.dao.api;

import java.util.List;

public interface GenericDAO <Entity> {
    void create(Entity entity);

    void update(Entity entity);

    void delete(Entity entity);

    Entity getById(Integer id);

    List<Entity> getAll();

}
