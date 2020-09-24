package com.empleado.solicitud.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.empleado.solicitud.models.entity.Empleado;
import com.empleado.solicitud.models.entity.Solicitud;

public interface ISolicitudDao extends PagingAndSortingRepository<Solicitud, Long>  {
	
	@Query("select idEmpleado,nombre from Empleado")
	public List<Empleado> findByAllE();
	
	
	

}
