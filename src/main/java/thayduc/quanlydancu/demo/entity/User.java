package thayduc.quanlydancu.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import thayduc.quanlydancu.demo.entity.security.Authority;
import thayduc.quanlydancu.demo.entity.security.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;


@NamedEntityGraph(
		name= "UserComplete",
		attributeNodes= { @NamedAttributeNode(value="userRoles", subgraph="role-subgraph") },
		subgraphs= {
				@NamedSubgraph(name = "role-subgraph", attributeNodes = {  @NamedAttributeNode("role") }
				)}
)
@Entity
@Data
@SuppressWarnings("serial")
public class User extends AbstractEntity implements UserDetails, Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private Long id;
	@NotNull
	private String username;
	private String password;
	private String hoTen;
	private  String imgAvatar;
	@Transient
	private String confirmPassword;
	@Length(min = 10, max =15)
	private String numberPhone;
	@Transient
	private MultipartFile file;
	@Transient
	private String passwordConfirm;
	@NotNull
	@Email
	private String email;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL )
	private List<BaiViet> baiViets;

	@OneToMany(mappedBy="user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<HoaDon> hoaDons;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;


	
	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(userRole -> authorites.add(new Authority(userRole.getRole().getName())));
		return authorites;
	}

	@Override
	public String toString() {
	  return getClass().getSimpleName() + "[id=" + id + "]" + "[username=" + username + "]" + "[password=" + password + "]" + "[email=" + email + "]";
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
	

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
}



