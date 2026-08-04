package com.devsuperior.dslist.utils.validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidCPF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // Deixe o @NotBlank lidar com nulos, se necessário
        }

        // Remove caracteres não numéricos
        String cpf = value.replaceAll("\\D", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma1 = 0;
            int soma2 = 0;
            int peso1 = 10;
            int peso2 = 11;

            for (int i = 0; i < 9; i++) {
                int num = cpf.charAt(i) - '0';
                soma1 += num * peso1--;
                soma2 += num * peso2--;
            }

            int resto1 = 11 - (soma1 % 11);
            int digito1 = (resto1 > 9) ? 0 : resto1;

            soma2 += digito1 * peso2;
            int resto2 = 11 - (soma2 % 11);
            int digito2 = (resto2 > 9) ? 0 : resto2;

            return digito1 == (cpf.charAt(9) - '0') && digito2 == (cpf.charAt(10) - '0');
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
