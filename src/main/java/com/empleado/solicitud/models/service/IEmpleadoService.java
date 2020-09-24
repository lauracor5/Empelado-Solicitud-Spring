package com.empleado.solicitud.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.empleado.solicitud.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();

	public Page<Empleado> findAll(Pageable pageable);

	public void saveEmploye(Empleado empleado);

	public Empleado findOne(Long idEmpleado);

}
