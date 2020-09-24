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
import com.empleado.solicitud.models.service.IEmpleadoService;

@Controller
@SessionAttributes("empleado")
public class EmpleadoController {

	@Autowired
	private IEmpleadoService iEmpleadoService;

	@RequestMapping(value = "/listarEmpleados", method = RequestMethod.GET)
	public String Listar(Model model) {
		model.addAttribute("titulo", "Listado de Empleados");
		model.addAttribute("empleados", iEmpleadoService.findAll());
		return "listarEmpleados";
	}

	@RequestMapping(value = "/formEmpleado")
	public String crear(Model model) {
		Empleado empleado = new Empleado();
		model.addAttribute("empleado", empleado);
		model.addAttribute("titulo", "Formulario de empleados");
		return "formEmpleado";
	}

	@RequestMapping(value = "/formEmpleado", method = RequestMethod.POST)
	public String guardar(@Valid Empleado empleado, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de empleados");
			return "formEmpleado";
		}
		iEmpleadoService.saveEmploye(empleado);
		status.setComplete();
		flash.addFlashAttribute("success", "Empleado creado con éxito");
		return "redirect:listarEmpleados";
	}

	@RequestMapping(value = "/verEmpleado/{idEmpleado}", method = RequestMethod.GET)
	public String verEmpleado(@PathVariable(value = "idEmpleado") Long idEmpleado, Model model,
			RedirectAttributes flash) {
		Empleado empleado = iEmpleadoService.findOne(idEmpleado);
		if (empleado == null) {
			flash.addFlashAttribute("error", "El empleado no existe en la base de datos");
			return "redirect:listarEmpleados";
		}
		model.addAttribute("titulo", "Información del Empleado " + empleado.getNombre());
		model.addAttribute("empleado", empleado);
		return "verEmpleado";
	}

}
