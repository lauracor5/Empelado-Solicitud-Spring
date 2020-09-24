package com.empleado.solicitud.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empleado.solicitud.models.dao.IEmpleadoDao;
import com.empleado.solicitud.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao iEmpleadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) iEmpleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {
		return iEmpleadoDao.findAll(pageable);
	}

	@Override
	public Empleado findOne(Long id) {
		return iEmpleadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void saveEmploye(Empleado empleado) {
		iEmpleadoDao.save(empleado);
	}

}
