package app.service;

import app.dto.LoanDto;

public interface MovenmentService {
	public void createLoan(LoanDto loanDto) throws Exception;
	public void deleteLoan(LoanDto loanDto) throws Exception;
}
