package com.empleado.solicitud.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.empleado.solicitud.models.entity.Empleado;
import com.empleado.solicitud.models.entity.Solicitud;

public interface ISolicitudService {
	
	public List<Solicitud> findAll();
	
	public Page<Solicitud> findAll(Pageable pageable);
	
	public void saveRequest(Solicitud solicitud);
	
	public Solicitud findOne(Long id);
	
	public List<Empleado> findAllEmpleado();
	
	public Empleado findById(Long id);
}
