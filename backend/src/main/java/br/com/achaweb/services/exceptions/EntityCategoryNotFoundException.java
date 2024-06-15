package br.com.achaweb.services.exceptions;

public class EntityCategoryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntityCategoryNotFoundException(String message) {
        super(message);
    }
}
