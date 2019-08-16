package uy.app.web.app.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table (name = "Trips")
public class EntTrip implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private long id;
	
	//@NotNull
	@Column(name = "Date")
	//@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	//@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date date;
	
	@Nullable
	private String dateSinFormato;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	private EntDrivers ents;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "trip_id1")
	private EntVehiculo veh;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EntDrivers getEnts() {
		return ents;
	}

	public void setEnts(EntDrivers ents) {
		this.ents = ents;
	}

	public EntVehiculo getVeh() {
		return veh;
	}

	public void setVeh(EntVehiculo veh) {
		this.veh = veh;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateSinFormato() {
		return dateSinFormato;
	}

	public void setDateSinFormato(String dateSinFormato) {
		this.dateSinFormato = dateSinFormato;
	}
}
