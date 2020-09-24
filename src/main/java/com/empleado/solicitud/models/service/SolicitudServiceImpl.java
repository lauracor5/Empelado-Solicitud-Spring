package com.empleado.solicitud.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empleado.solicitud.models.dao.IEmpleadoDao;
import com.empleado.solicitud.models.dao.ISolicitudDao;
import com.empleado.solicitud.models.entity.Empleado;
import com.empleado.solicitud.models.entity.Solicitud;

@Service
public class SolicitudServiceImpl implements ISolicitudService {
	
	@Autowired
	private ISolicitudDao iSolicitudDao;
	
	@Autowired
	private IEmpleadoDao iEmpleadoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Solicitud> findAll() {		
		return (List<Solicitud>) iSolicitudDao.findAll() ;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Solicitud> findAll(Pageable pageable) {		
		return iSolicitudDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void saveRequest(Solicitud solicitud) {
		iSolicitudDao.save(solicitud);
	}

	@Override
	@Transactional(readOnly = true)
	public Solicitud findOne(Long id) {		
		return iSolicitudDao.findById(id).orElse(null);
	}



	@Override
	public List<Empleado> findAllEmpleado() {
		return iSolicitudDao.findByAllE();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Long id) {
		return iEmpleadoDao.findById(id).orElse(null);
	}


	

}
