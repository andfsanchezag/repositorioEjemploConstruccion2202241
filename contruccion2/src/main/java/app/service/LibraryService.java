package app.service;

import java.util.Arrays;
import java.util.List;

import app.dao.LoanDao;
import app.dao.LoanDaoImp;
import app.dao.LoginDao;
import app.dao.LoginDaoImp;
import app.dao.MaterialDao;
import app.dao.MaterialDaoImp;
import app.dao.MovementDao;
import app.dao.MovementDaoImp;
import app.dao.PersonDao;
import app.dao.PersonDaoImpl;
import app.dto.LoanDto;
import app.dto.MaterialDto;
import app.dto.MovementDto;
import app.dto.PersonDto;
import app.dto.SessionDto;

public class LibraryService implements LibrarianService, MovenmentService, LoginService {
	List<String> roles = Arrays.asList("Estudiante", "Administrativo", "Bliblitecario", "Profesor");
	private static long sessionId = 0L;

	@Override
	public void createUser(PersonDto personDto) throws Exception {
		if (!roles.contains(personDto.getRol())) {
			throw new Exception("el rol no es valido");
		}
		PersonDao personDao = new PersonDaoImpl();
		if (personDao.findUserExist(personDto)) {
			throw new Exception("ya existe un usuario con esa cedula");
		}
		if (personDao.existUserByUserName(personDto)) {
			throw new Exception("ya existe el usuario");
		}
		personDao.createPerson(personDto);
		System.out.println("se ha creado el usuario");
	}

	@Override
	public void createMaterial(MaterialDto materialDto) throws Exception {
		MaterialDao materialDao = new MaterialDaoImp();
		materialDao.createMaterial(materialDto);
		System.out.println("se ha creado el material");
	}

	@Override
	public void createLoan(LoanDto loanDto) throws Exception {
		LoginDao loginDao = new LoginDaoImp();
		SessionDto sessionDto = loginDao.findSessionById(sessionId);
		if (sessionDto == null) {
			throw new Exception("no hay una sesion valida");
		}
		PersonDao personDao = new PersonDaoImpl();
		PersonDto personDto = new PersonDto(sessionDto.getUserName(), "");
		personDto = personDao.findUserByUserName(personDto);
		if (personDto == null) {
			throw new Exception("no hay un usuario valido");
		}
		MaterialDao materialDao = new MaterialDaoImp();
		MaterialDto materialDto = materialDao.findMaterialById(loanDto.getMaterial());
		if (materialDto == null) {
			throw new Exception("id de material invalido");
		}
		if (materialDto.getActQuantity() == 0) {
			throw new Exception("no hay unidades disponibles");
		}
		LoanDao loanDao = new LoanDaoImp();
		List<LoanDto> loans = loanDao.findLoansByUserId(personDto);
		if ((personDto.getRol().equals("Profesor") && loans.size() == 3)
				|| (personDto.getRol().equals("Estudiante") && loans.size() == 5)
				|| (personDto.getRol().equals("Administrativo") && loans.size() == 1)) {
			throw new Exception("ha alcanzado el limite de prestamos");
		}
		materialDto.setActQuantity(materialDto.getActQuantity()-1);
		materialDao.updateMaterial(materialDto);
		loanDto.setPerson(personDto);
		loanDao.createLoan(loanDto);
		MovementDao movenmentDao = new MovementDaoImp();
		MovementDto movementDto = new MovementDto(personDto,materialDto,"prestamo");
		movenmentDao.createMovenment(movementDto);
		System.out.println("prestamo exitoso");
	}
	@Override
	public void deleteLoan(LoanDto loanDto)throws Exception{
		LoginDao loginDao = new LoginDaoImp();
		SessionDto sessionDto = loginDao.findSessionById(sessionId);
		if (sessionDto == null) {
			throw new Exception("no hay una sesion valida");
		}
		PersonDao personDao = new PersonDaoImpl();
		PersonDto personDto = new PersonDto(sessionDto.getUserName(), "");
		personDto = personDao.findUserByUserName(personDto);
		if (personDto == null) {
			throw new Exception("no hay un usuario valido");
		}
		MaterialDao materialDao = new MaterialDaoImp();
		MaterialDto materialDto = materialDao.findMaterialById(loanDto.getMaterial());
		if (materialDto == null) {
			throw new Exception("id de material invalido");
		}
		materialDto.setActQuantity(materialDto.getActQuantity()+1);
		materialDao.updateMaterial(materialDto);
		LoanDao loanDao = new LoanDaoImp();
		loanDto.setPerson(personDto);
		loanDao.deleteLoan(loanDto);
		MovementDao movenmentDao = new MovementDaoImp();
		MovementDto movementDto = new MovementDto(personDto,materialDto,"devolucion");
		movenmentDao.createMovenment(movementDto);
		System.out.println("prestamo exitoso");
	}

	@Override
	public void setSesionID(long sesionId) {
		sessionId = sesionId;
	}

	@Override
	public void login(PersonDto personDto) throws Exception {
		PersonDao personDao = new PersonDaoImpl();
		PersonDto personDtoValidate = personDao.findUserByUserName(personDto);
		if (personDtoValidate == null) {
			throw new Exception("usuario no valido");
		}
		if (!personDto.getPassword().equals(personDtoValidate.getPassword())) {
			throw new Exception("usuario o contraseña incorrectos");
		}
		personDto.setRol(personDtoValidate.getRol());
		LoginDao loginDao = new LoginDaoImp();
		SessionDto sesionDto = loginDao.login(personDtoValidate);
		setSesionID(sesionDto.getId());
		System.out.println("se inicia la sesion " + sessionId);
	}

	@Override
	public void logout() throws Exception {
		LoginDao loginDao = new LoginDaoImp();
		loginDao.logout(sessionId);
		setSesionID(0);
	}

}
