package models.eav;

import models.AbstractModel;

import java.util.Set;

public class EntityTypeCategory extends AbstractModel {

    private int id;
    private String name;
    private Set<EntityType> entityTypes;

    public EntityTypeCategory(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EntityType> getEntityTypes() {
        return entityTypes;
    }

    public void setEntityTypes(Set<EntityType> entityTypes) {
        this.entityTypes = entityTypes;
    }

    /*  -- loop detection --

    WITH RECURSIVE search_graph(parent, child, id, depth, path, cycle)
    AS (
            SELECT e.parent, e.child, e.id, 1,
              ARRAY[e.id],
              false
            FROM networking.netdevices e
          UNION ALL
            SELECT e.parent, e.child, e.id, sg.depth + 1,
              path || e.id,
              e.id = ANY(path)
            FROM edge e, search_graph sg
            WHERE e.parent = sg.child AND NOT cycle
    )
    SELECT * FROM search_graph;

    with recursive netdevice  as (
        select node_id, 1 AS GenerationsRemoved
        from networking.netdevices
        union all
        select n.node_id, n.GenerationsRemoved + 1
        from netdevice as n
        inner join networking.netdevices on n.node_id = parent_dev_id
        and n.GenerationsRemoved < 6
        )
    select *
    from netdevice
    where GenerationsRemoved > 5

    -----------------------------------

    WITH RECURSIVE prev AS (
            SELECT devs.node_id, 1 AS depth, array[node_id] as seen, false as cycle
            FROM networking.netdevices AS devs
            UNION ALL
            SELECT devs.node_id, prev.depth + 1, seen || devs.node_id as seen,
                devs.node_id = any(seen) as cycle
            FROM prev
            INNER JOIN networking.netdevices AS devs on prev.node_id = parent_dev_id
            AND prev.cycle = false
        )
        SELECT *
        FROM prev
        WHERE cycle = true;

    */
}
