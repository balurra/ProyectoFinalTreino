package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IProfesorDao;
import com.example.demo.entity.Profesor;
import com.example.demo.entity.Rol;
import com.example.demo.entity.VisibilidadPerfil;

@Service
public class ProfesorServiceImpl implements IProfesorService{
	
	@Autowired
	private IProfesorDao profesorDao;

	@Override
	@Transactional(readOnly = true)
	public List<Profesor> findAll() {
		return (List<Profesor>) profesorDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findProfesor(Profesor profesor) {
		return (Profesor) profesorDao.findByEmail(profesor.getEmail());
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor checkProfesorLogin(Profesor profesor) {
		return (Profesor) profesorDao.findByEmailAndPassword(profesor.getEmail(), profesor.getPassword());
	}

	@Override
	@Transactional
	public void deleteProfesor(Profesor profesor) {
		profesorDao.deleteById(profesor.getId());
	}

	@Override
	@Transactional
	public Profesor updateProfesor(Profesor profesor) {
		return (Profesor) profesorDao.save(profesor);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Profesor> findProfesorById(Long profesor_id) {
		return (Optional<Profesor>) profesorDao.findById(profesor_id);
	}

	@Override
	@Transactional
	public void deleteProfesor(Long id) {
		profesorDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findById(Long id) {
		return (Profesor) profesorDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Profesor findByIdSQL(Long id) {
		return profesorDao.findBySQL(id);
	}

	@Override
	@Transactional
	public void save(Profesor profesor) {
		profesor.setRol(Rol.TRAINER);
		profesorDao.save(profesor);
		
	}

	@Override
	@Transactional
	public void deleteAllProfesores() {
		profesorDao.deleteAll();
		
	}

	@Override
	public Profesor findProfesorByEmail(String email) {
		
		return profesorDao.findByEmail(email);
	}

	@Override
	public List<Profesor> findProfesoresPublicados() {
	        List<Profesor> allProfesores = (List<Profesor>) profesorDao.findAll(); // Obtener todos los profesores
	        return allProfesores.stream()
	                .filter(p -> p.isPerfilCompleto() && p.getMembresia() != null && p.getVisibilidadPerfil() == VisibilidadPerfil.PUBLIC)
	                .collect(Collectors.toList());
	    
	}

}
