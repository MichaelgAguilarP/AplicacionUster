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
@Table (name = "Vehiculos")
public class EntVehiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	@Column(name= "Brand")
	@NotEmpty
	private String brand;
	
	@Column(name= "Model")
	@NotEmpty
	private String model;
	
	@Column(name= "Plate")
	@NotEmpty
	private String plate;
	
	@Column(name= "License")
	@NotEmpty
	@Size(min=1, max=1)
	private String license;
	
	@OneToMany(mappedBy= "veh",fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private List<EntTrip> tri;
	
	public EntVehiculo () {
		tri = new ArrayList<EntTrip>();
	}
	
	public void prePersist() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
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
