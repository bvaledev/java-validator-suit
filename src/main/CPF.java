package main;

public class CPF {
    private String removeNonDigits(String value) {
        return value.replaceAll("\\D+", "");
    }

    private boolean hasTheSameDigit(String value) {
        char firstDigit = value.charAt(0);
        int equalLengthQuantity = 0;
        for (char digit : value.toCharArray()) {
            if (firstDigit == digit) {
                equalLengthQuantity += 1;
            }
        }
        return equalLengthQuantity == value.length();
    }

    private boolean hasValidLength(String value) {
        int length = value.length();
        return (length == 11 || length == 14) ? true : false;
    }

    private String extractCheckDigit(String value) {
        int length = value.length();
        return value.substring(length - 2);
    }

    private int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (char digit : cpf.toCharArray()) {
            if (factor > 1) {
                total += Character.getNumericValue(digit) * factor--;
            }
        }
        int rest = total % 11;
        return (rest < 2) ? 0 : 11 - rest;
    }

    public boolean validate(String cpf) {

        if (cpf == null || cpf.isEmpty() || cpf.isBlank() || !this.hasValidLength(cpf)) {
            return false;
        }
        String cpfDigits = this.removeNonDigits(cpf);
        if (this.hasTheSameDigit(cpfDigits)) {
            return false;
        }
        int firstDigitFactor = 10;
        int secondDigitFactor = 11;
        int digitOne = this.calculateDigit(cpfDigits, firstDigitFactor);
        int digitTwo = this.calculateDigit(cpfDigits, secondDigitFactor);
        String checkDigit = this.extractCheckDigit(cpf);
        String checkDigitCalculated = String.format("%d%d", digitOne, digitTwo);
        return checkDigitCalculated.equals(checkDigit);
    }
}
