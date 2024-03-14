package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.config.MYSQLConnection;
import app.dto.LoanDto;
import app.dto.PersonDto;

public class LoanDaoImp implements LoanDao {
	public Connection connection = MYSQLConnection.getConnection();

	@Override
	public List<LoanDto> findLoansByUserId(PersonDto personDto) throws Exception {
		String query = "SELECT ID,PERSON_ID, MATERIAL_ID FROM LOAN WHERE PERSON_ID = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, personDto.getId());
		ResultSet resulSet = preparedStatement.executeQuery();
		List<LoanDto> loans = new ArrayList();
		while (resulSet.next()) {
			long personId = resulSet.getLong("PERSON_ID");
			int materialId = resulSet.getInt("MATERIAL_ID");
			loans.add(new LoanDto(personId, materialId));
		}
		resulSet.close();
		preparedStatement.close();
		return loans;
	}

	@Override
	public void createLoan(LoanDto loanDto) throws Exception {
		String query = "INSERT INTO LOAN(PERSON_ID,MATERIAL_ID) VALUES (?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setLong(i++, loanDto.getPerson().getId());
		preparedStatement.setInt(i++, loanDto.getMaterial().getId());
		preparedStatement.execute();
		preparedStatement.close();

	}

	@Override
	public void deleteLoan(LoanDto loanDto) throws Exception {
		String query = "DELETE FROM LOAN WHERE PERSON_ID = ? AND MATERIAL_ID =?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		int i = 1;
		preparedStatement.setLong(i++, loanDto.getPerson().getId());
		preparedStatement.setInt(i++, loanDto.getMaterial().getId());
		preparedStatement.execute();
		preparedStatement.close();
	}

}
