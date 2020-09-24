package com.empleado.solicitud.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.empleado.solicitud.models.entity.Empleado;



public interface IEmpleadoDao extends  PagingAndSortingRepository<Empleado, Long>  {

}
