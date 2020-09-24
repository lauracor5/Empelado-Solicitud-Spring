package com.empleado.solicitud.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

	
	@Entity
	@Table(name = "empleados")
	public class Empleado implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long idEmpleado;

		@Column(name = "fecha_ingreso")
		@Temporal(TemporalType.DATE)
		private Date fechaIngreso;

		@NotEmpty
		private String nombre;

		@NotNull
		private Double salario;

		@OneToMany(mappedBy = "empleado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
		private List<Solicitud> solicitudes;

		@PrePersist
		public void prePersist() {
			fechaIngreso = new Date();
		}
		
		public Empleado() {
			solicitudes = new ArrayList<>();
		}

		// metodos
		public Long getIdEmpleado() {
			return idEmpleado;
		}

		public void setIdEmpleado(Long idEmpleado) {
			this.idEmpleado = idEmpleado;
		}

		public Date getFechaIngreso() {
			return fechaIngreso;
		}

		public void setFechaIngreso(Date fechaIngreso) {
			this.fechaIngreso = fechaIngreso;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Double getSalario() {
			return salario;
		}

		public void setSalario(Double salario) {
			this.salario = salario;
		}

		public List<Solicitud> getSolicitudes() {
			return solicitudes;
		}

		public void setSolicitudes(List<Solicitud> solicitudes) {
			this.solicitudes = solicitudes;
		}

		public void addSolicitud(Solicitud solicitud) {
			solicitudes.add(solicitud);
		}

		private static final long serialVersionUID = 1L;

	}



