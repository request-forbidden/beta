package models;

public class Group extends AbstractModel {

	private long id;
	private String name;
	private String descr;

	public Group() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

/*
	public static void saveRoles(JsonNode jn) {

		ArrayNode roles = (ArrayNode) jn.get("privileges");

		Long group_id = jn.get("group_id").asLong();

		String q1 = " DELETE FROM group_privilege WHERE group_id = ? ";

		MyJPA.em().createNativeQuery(q1).setParameter(1, group_id).executeUpdate();

		StringBuilder q2 = new StringBuilder();

		q2.append(" INSERT INTO group_privilege (group_id, privilege_id) VALUES ");

		Iterator<JsonNode> ir = roles.iterator();

		if(ir.hasNext()){

			while(ir.hasNext()){

				Integer element = ir.next().asInt();
	
				q2.append("("+group_id+", "+element+")");
	
				if(ir.hasNext()){
					q2.append(", ");
				}else{
					q2.append(";");
				}
	
			}
	
			MyJPA.em().createNativeQuery(q2.toString()).executeUpdate();
		}
	}

	public Group(String name, String descr) {
		this.name = name;
		this.descr = descr;
	}
	
	public static List<Group> getList(){
		Session session = ((Session) JPA.em().getDelegate());
		Criteria criteria = session.createCriteria(Group.class);
		@SuppressWarnings("unchecked")
		List<Group> l = criteria.list();
		return l;
	}

	public static Set<Group> getUserList(Long userId){

		return User.findById(userId).getGroups();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

     public static void removeUserFromGroup(long userId, long groupId) {
		MyJPA.em().createNativeQuery(" DELETE FROM user_group WHERE user_id = ? AND group_id = ? ")
		.setParameter(1, userId)
		.setParameter(2, groupId)
		.executeUpdate();
	}

*/


}