package app.controller;

import java.util.Scanner;

import app.dto.PersonDto;
import app.service.LibraryService;
import app.service.LoginService;
import app.validators.PersonInputsValidator;

public class LoginController {
	private static Scanner reader = new Scanner(System.in);
	private static PersonInputsValidator personInputValidator = new PersonInputsValidator();
	private static LoginService loginService = new LibraryService();
	private static LibrarianController librarianController = new LibrarianController();
	private static MovementController movenmentController = new MovementController();

	public void login() throws Exception {
		System.out.println("ingrese su usuario");
		String userName = reader.nextLine();
		personInputValidator.userNameValidator(userName);
		System.out.println("ingrese su contraseña");
		String password = reader.nextLine();
		personInputValidator.passwordValidator(password);
		PersonDto personDto = new PersonDto(userName, password);
		loginService.login(personDto);
		loginRouter(personDto);
		loginService.logout();
	}

	private void loginRouter(PersonDto personDto) {
		if (personDto.getRol().equals("Bibliotecario")) {
			librarianController.session();
		} else {
			movenmentController.session();
		}
	}
}
