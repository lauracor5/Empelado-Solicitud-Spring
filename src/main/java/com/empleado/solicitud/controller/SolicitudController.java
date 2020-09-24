package com.empleado.solicitud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empleado.solicitud.models.entity.Empleado;
import com.empleado.solicitud.models.entity.Solicitud;
import com.empleado.solicitud.models.service.IEmpleadoService;
import com.empleado.solicitud.models.service.ISolicitudService;

@SessionAttributes
@Controller
public class SolicitudController {

	@Autowired
	private ISolicitudService solicitudService;
	
	@Autowired
	private IEmpleadoService empleadoService;

	@RequestMapping(value = "/listarSolicitudes", method = RequestMethod.GET)
	public String listarSolicitudes(Model model) {
		model.addAttribute("titulo", "Listado de solicitudes");
		model.addAttribute("solicitudes", solicitudService.findAll());
		return "listarSolicitudes";
	}

	@RequestMapping(value = "/formSolicitud")
	public String crear(Model model) {
		Solicitud solicitud = new Solicitud();
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("titulo", "Formulario de solicitud");
		model.addAttribute("empleados", empleadoService.findAll());
		return "formSolicitud";
	}

	@RequestMapping(value = "/formSolicitud", method = RequestMethod.POST)
	public String guardar(@Valid Solicitud solicitud, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de solicitud");
			return "formSolicitud";
		}
		solicitudService.saveRequest(solicitud);		
		status.setComplete();
		flash.addFlashAttribute("success", "Solicitud creada  con éxito");
		return "redirect:listarSolicitudes";
	}
	
	@RequestMapping(value = "/verSolicitud/{idSolicitud}", method = RequestMethod.GET)
	public String verEmpleado(@PathVariable(value = "idSolicitud") Long idSolicitud, Model model, RedirectAttributes flash) {
		Solicitud solicitud = solicitudService.findOne(idSolicitud);			
		if (solicitud == null) {
			flash.addAttribute("error", "La solicitud no existe en la base de datos");
			return "redirect:listarSolicitudes";
		}
		model.addAttribute("titulo", "Información de la solicitud " + solicitud.getCodigo());
		model.addAttribute("solicitud", solicitud);
		model.addAttribute("empleado", empleadoService.findOne(solicitud.getEmpleado().getIdEmpleado()));
		
		return "verSolicitud";
	}

}
