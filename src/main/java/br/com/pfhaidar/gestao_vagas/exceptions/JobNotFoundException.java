package br.com.pfhaidar.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException() {
        super("Vaga não encontrada");
    }
}
