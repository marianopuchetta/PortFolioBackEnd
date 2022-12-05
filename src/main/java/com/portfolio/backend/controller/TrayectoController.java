package com.portfolio.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.backend.model.Trayecto;
import com.portfolio.backend.service.ITrayectoService;



/**
*@author Mariano Puchetta
*23 nov. 2022
*/
@RestController
public class TrayectoController {

@Autowired
private ITrayectoService iTrayecto;



	@GetMapping("/trayectos")
	@ResponseBody
	public List <Trayecto> AllTrayectos() {
		return iTrayecto.getAllTrayectos();
	}
	
	@PostMapping("/newtrayecto")
	public ResponseEntity<Trayecto> addTrayecto(@RequestBody Trayecto trayecto) {
		iTrayecto.newTrayecto(trayecto);
		return new ResponseEntity<Trayecto>(trayecto, HttpStatus.OK);

	}
	
	@DeleteMapping("/deletetrayecto/{id}")
	public void deleteTrayecto(@PathVariable Long id) {
		iTrayecto.deleteTrayecto(id);
	}
	
	@PutMapping("edittrayecto/{id}")
	public ResponseEntity<Trayecto> editTrayecto(@PathVariable Long id,
							@RequestParam("institucion") String newInstitucion,
							@RequestParam("titulo") String newTitulo,
							@RequestParam("desde") String newDesde,
							@RequestParam("hasta") String newHasta) {
		
		Trayecto trayectoToEdit = iTrayecto.getTrayecto(id);
		
		trayectoToEdit.setInstitucion(newInstitucion);
		trayectoToEdit.setTitulo(newTitulo);
		trayectoToEdit.setDesde(newDesde);
		trayectoToEdit.setHasta(newHasta);
		
		iTrayecto.newTrayecto(trayectoToEdit);
		
		return new ResponseEntity<Trayecto>(trayectoToEdit,HttpStatus.OK);
	}
}


