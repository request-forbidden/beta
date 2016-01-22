package models;

//import be.objectify.deadbolt.core.models.Role;
import play.db.jpa.JPA;

import java.util.List;

public class SecurityRole  { //implements Role

	private Long id;
	private String name;
	private Long userId;
    private String descr;
    private Long categoryId;

	public SecurityRole(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name){
		this.name = name;
	}

//	@Override
	public String getName() {
		return this.name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

/*
	public static List<SecurityRole> getDutyPrivileged(){
		return JPA.em().createQuery(" from SecurityRole where categoryId = ?1 ").setParameter(1 ,5L).getResultList();
	}
*/

}