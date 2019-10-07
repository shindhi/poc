package application;

import methods.ValidCPF;

import java.util.Locale;

public class Program {
    public static void main(String[] args) {


        String cpf = "475.915.388-88";

        boolean valido = ValidCPF.valida(cpf);
        System.out.println("----------------");
        System.out.println("Valid: " + valido);

    }
}
