package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entity.Moto;
import com.moto.service.servicio.MotoServicio;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoServicio motoServicio;

	@GetMapping
	public ResponseEntity<List<Moto>> ListarMotos() {

		List<Moto> moto = motoServicio.getAll();

		if (moto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(moto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id") int id) {
		Moto moto = motoServicio.getMotoById(id);
		if (moto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(moto);
	}

	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto) {
		Moto nuevoMoto = motoServicio.save(moto);
		return ResponseEntity.ok(nuevoMoto);
	}

	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotoPorUsuario(@PathVariable("usuarioId") int id) {

		List<Moto> motos = motoServicio.byUsuarioId(id);

		if (motos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(motos);
	}

}
