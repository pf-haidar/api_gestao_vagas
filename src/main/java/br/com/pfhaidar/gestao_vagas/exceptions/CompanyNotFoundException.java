package br.com.pfhaidar.gestao_vagas.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException() {
        super("Usuario ja existe");
    }
}
