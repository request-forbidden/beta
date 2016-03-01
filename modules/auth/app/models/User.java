package models;

import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Role;
import be.objectify.deadbolt.core.models.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.DateTime;

import java.util.List;


public class User extends AbstractModel  implements Subject {

	private Long 	id;
	private String 	username;
	private String 	firstname;
	private String 	lastname;

	@JsonIgnore private String 	passwd;
	@JsonIgnore private String 	repasswd;

	private Boolean active;
	private DateTime lastLoginDate;
	private String 	lastLoginUseragent;
	private String 	lastLoginFrom;

	@JsonIgnore  private String 	lastLoginHash;

	private String 	remoteAddress;
	private String 	userAgent;
	private boolean rememberme;
	private String 	hash;
	private Boolean hasAvatar;

	private List<SecurityRole> roles;

	public User(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRepasswd() {
		return repasswd;
	}

	public void setRepasswd(String repasswd) {
		this.repasswd = repasswd;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public DateTime getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(DateTime lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginUseragent() {
		return lastLoginUseragent;
	}

	public void setLastLoginUseragent(String lastLoginUseragent) {
		this.lastLoginUseragent = lastLoginUseragent;
	}

	public String getLastLoginFrom() {
		return lastLoginFrom;
	}

	public void setLastLoginFrom(String lastLoginFrom) {
		this.lastLoginFrom = lastLoginFrom;
	}

	public String getLastLoginHash() {
		return lastLoginHash;
	}

	public void setLastLoginHash(String lastLoginHash) {
		this.lastLoginHash = lastLoginHash;
	}

	public String getRemoteAddress() {
		return remoteAddress;
	}

	public void setRemoteAddress(String remoteAddress) {
		this.remoteAddress = remoteAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public boolean isRememberme() {
		return rememberme;
	}

	public void setRememberme(boolean rememberme) {
		this.rememberme = rememberme;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Boolean getHasAvatar() {
		return hasAvatar;
	}

	public void setHasAvatar(Boolean hasAvatar) {
		this.hasAvatar = hasAvatar;
	}

	@Override
	public List<? extends Role> getRoles() {
		return null;
	}


	@Override
	public List<? extends Permission> getPermissions() {
		return null;
	}

	@Override
	public String getIdentifier() {
		return null;
	}

	public void setRoles(List<SecurityRole> roles) {
		this.roles = roles;
	}
}