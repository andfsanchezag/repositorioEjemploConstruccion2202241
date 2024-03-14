package app.dao;

import java.util.List;

import app.dto.LoanDto;
import app.dto.PersonDto;

public interface LoanDao {
	public List<LoanDto> findLoansByUserId(PersonDto personDto) throws Exception;

	public void createLoan(LoanDto loanDto) throws Exception;
	public void deleteLoan(LoanDto loanDto) throws Exception;
}
