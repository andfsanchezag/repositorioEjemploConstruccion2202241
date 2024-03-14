package app.controller;

import java.util.Scanner;

import app.dto.MaterialDto;
import app.dto.PersonDto;
import app.service.LibrarianService;
import app.service.LibraryService;
import app.validators.MaterialInputsValidator;
import app.validators.PersonInputsValidator;

public class LibrarianController {
	private static PersonInputsValidator personInputValidator = new PersonInputsValidator();
	private static MaterialInputsValidator materialInputValidator = new MaterialInputsValidator();
	private static Scanner reader = new Scanner(System.in);
	private static LibrarianService librarianService = new LibraryService();
	private static final String MENU = "ingrese\n1.Para crear usuario\n2.Para crear Material\n3.Para cerrar Sesion";

	private void createUser() throws Exception {
		System.out.println("ingrese el nombre completo");
		String fullName = reader.nextLine();
		personInputValidator.fullNameValidator(fullName);
		System.out.println("ingrese la cedula del usuario");
		Long id = personInputValidator.idValidator(reader.nextLine());
		System.out.println("ingrese el rol completo");
		String rol = reader.nextLine();
		personInputValidator.fullNameValidator(rol);
		System.out.println("ingrese nombre de usuario");
		String userName = reader.nextLine();
		personInputValidator.fullNameValidator(userName);
		System.out.println("ingrese la contraseña");
		String password = reader.nextLine();
		personInputValidator.fullNameValidator(password);
		PersonDto personDto = new PersonDto(id, fullName, rol, userName, password);
		//System.out.println("se cumplieron todas las validaciones");
		librarianService.createUser(personDto);
	}

	private void createMaterial() throws Exception {
		System.out.println("ingrese el titulo del material");
		String tittle = reader.nextLine();
		materialInputValidator.tittleValidator(tittle);
		int quantity = materialInputValidator.quantityValidator(reader.nextLine());
		MaterialDto materialDto = new MaterialDto(tittle, quantity);
		librarianService.createMaterial(materialDto);
	}

	public void session() {
		boolean runApp = true;
		while (runApp) {
			try {
				System.out.println(MENU);
				String option = reader.nextLine();
				runApp=menu(option);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	private boolean menu(String option) throws Exception{
		switch (option) {
		case "1":{
			createUser();
			return true;
		}
		case "2": {
			createMaterial();
			return true;
		}
		case "3": {
			return false;
		}
		default :{
			System.out.println("ingrese una opcion valida");
			return true;
		}
		}
	}

}
