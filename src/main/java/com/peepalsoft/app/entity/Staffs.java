package com.peepalsoft.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table
public class Staffs extends BaseEntity{
		
	@Id
	private String code;

	@NotNull
	@Size(min = 4, max = 30)
	private String firstName;
	
	@NotNull
	@Size(min = 4, max = 30)
	private String lastName;
	
	private String gender;
	
	private String phoneNumber;

	@NotEmpty
	@Size(min = 4, max = 50)
	private String post;
	
	@OneToOne
	private Office office;
	
	@Lob
    @Column(columnDefinition="mediumblob", nullable = true)
    private byte[] pic;
	
	@Transient
	private String base64pic;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy = "staffs",cascade = CascadeType.ALL)
	private Set<StaffsFamily> staffsFamily;
	
	
	public String getBase64pic() {
		return base64pic;
	}
	public void setBase64pic(String base64pic) {
		this.base64pic = base64pic;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public Set<StaffsFamily> getStaffsFamily() {
		return staffsFamily;
	}
	public void setStaffsFamily(Set<StaffsFamily> staffsFamily) {
		this.staffsFamily = staffsFamily;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
	
	
	
}
