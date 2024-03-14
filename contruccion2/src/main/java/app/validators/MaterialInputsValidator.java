package app.validators;

public class MaterialInputsValidator extends InputsValidator {
	public int idValidator(String string) throws Exception{
		return super.integerValidator(string, "id del material");
	}
	public int quantityValidator(String number) throws Exception{
		return super.integerValidator(number, "cantidad del material");
	}
	public int actQuantityValidator(String number) throws Exception{
		return super.integerValidator(number, "cantidad actual del material");
	}
	public void tittleValidator(String string) throws Exception{
		super.stringValidator(string, "el titulo del material");
	}
}
