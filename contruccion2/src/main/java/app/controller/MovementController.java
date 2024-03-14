package app.controller;

import java.util.Scanner;

import app.dto.LoanDto;
import app.dto.MaterialDto;
import app.service.LibraryService;
import app.service.MovenmentService;
import app.validators.MaterialInputsValidator;
import app.validators.PersonInputsValidator;

public class MovementController {
	private static PersonInputsValidator personInputValidator = new PersonInputsValidator();
	private static MaterialInputsValidator materialInputValidator = new MaterialInputsValidator();
	private static Scanner reader = new Scanner(System.in);
	private static MovenmentService loanService = new LibraryService();
	private static final String MENU="ingrese\n1.Para Prestar un material\n2.Para devolver un material\n3.Para cerrar Sesion";

	private void createLoan() throws Exception {
		System.out.println("ingrese el id del material");
		int materialId = materialInputValidator.idValidator(reader.nextLine());
		MaterialDto materialDto = new MaterialDto(materialId);
		LoanDto loanDto = new LoanDto();
		loanDto.setMaterial(materialDto);
		loanService.createLoan(loanDto);
	}
	private void deleteLoan() throws Exception {
		System.out.println("ingrese el id del material");
		int materialId = materialInputValidator.idValidator(reader.nextLine());
		MaterialDto materialDto = new MaterialDto(materialId);
		LoanDto loanDto = new LoanDto();
		loanDto.setMaterial(materialDto);
		loanService.deleteLoan(loanDto);
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
			createLoan();
			return true;
		}
		case "2": {
			deleteLoan();
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
