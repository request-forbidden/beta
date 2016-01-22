package models;

public class Privilege {

	private Long id;
	private String name;
	private String descr;
	private String roleName;
	private Long categoryId;
	private boolean userAvailable;
	private Category category;

	private boolean checked;

	public Privilege(){}

	public static class GroupPrivileges {
		
		private long groupId;
		private long privilegeId;
		
		protected GroupPrivileges(){}

		public long getGroupId() {
			return groupId;
		}

		public void setGroupId(long groupId) {
			this.groupId = groupId;
		}

		public long getPrivilegeId() {
			return privilegeId;
		}

		public void setPrivilegeId(long privilegeId) {
			this.privilegeId = privilegeId;
		}

	}

	public static class Category {

		private long id;
		private String name;

		protected Category(){ }

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isUserAvailable() {
		return userAvailable;
	}

	public void setUserAvailable(boolean userAvailable) {
		this.userAvailable = userAvailable;
	}



		/*




        public static List<Privilege> getPrivileges(){

            Session session = ((Session) JPA.em().getDelegate());
            Criteria criteria = session.createCriteria(Privilege.class);
            criteria.createAlias("category", "c", JoinType.LEFT_OUTER_JOIN);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            @SuppressWarnings("unchecked")
            List<Privilege> l = criteria.list();

            return l;
        }

        public static List<Privilege> getUserAvailablePrivileges(){

            Session session = ((Session) JPA.em().getDelegate());
            Criteria criteria = session.createCriteria(Privilege.class);
            criteria.createAlias("category", "c", JoinType.LEFT_OUTER_JOIN);
            criteria.add(Restrictions.eq("userAvailable", true));
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            @SuppressWarnings("unchecked")
            List<Privilege> l = criteria.list();

            return l;
        }

        public static List<Integer> getGroupPrivilages(Long id){

            @SuppressWarnings("unchecked")
            List<Integer> l = JPA.em().createNativeQuery(" SELECT privilege_id FROM group_privilege WHERE group_id = ?1 ").setParameter(1, id.intValue()).getResultList();

            return l;
        }

        public static List<Integer> getUserAvailablePrivilages(Long id) {

            @SuppressWarnings("unchecked")
            List<Integer> l = JPA.em().createNativeQuery(" SELECT privilege_id FROM user_privilege WHERE user_id = ?1 ").setParameter(1, id.intValue()).getResultList();

            return l;
        }


        public static List<GroupPrivileges> getGroupPrivilages2(Long id){

            Session session = ((Session) JPA.em().getDelegate());
            Criteria criteria = session.createCriteria(GroupPrivileges.class);
            criteria.add(Restrictions.eq("groupId", id));
            @SuppressWarnings("unchecked")
            List<GroupPrivileges> l = criteria.list();

            return l;
        }

    */



}
