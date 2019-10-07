package methods;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CPFValidator;

import java.util.List;

public class ValidCPF {
    private String cpf;

    public static boolean valida(String cpf) {
        CPFValidator cpfValidator = new CPFValidator();
        List<ValidationMessage> erros = cpfValidator.invalidMessagesFor(cpf);

        if (erros.size() > 0) {
            return false;
        }

        return true;
    }
}
