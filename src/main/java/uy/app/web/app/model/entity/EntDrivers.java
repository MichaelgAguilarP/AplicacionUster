package uy.app.web.app.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Drivers")
public class EntDrivers implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name= "Name")
	@NotEmpty
	private String name;
	
	@Column(name= "Surname")
	@NotEmpty
	private String surname;
	
	@Column(name= "License")
	@NotEmpty
	@Size(min=1, max=1)
	private String license;
	
	@OneToMany(mappedBy= "ents", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EntTrip> tri;
	
	public EntDrivers () {
		tri = new ArrayList<EntTrip>();
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public List<EntTrip> getTri() {
		return tri;
	}

	public void setTri(List<EntTrip> tri) {
		this.tri = tri;
	}
	
	public void addTrip(EntTrip tr) {
		tri.add(tr);
	}
}