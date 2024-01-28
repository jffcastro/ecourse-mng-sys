package application.base.domain.examManagement.valueObjects;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;

@Embeddable
public class Language implements ValueObject {

    private static final long serialVersionUID = 1L;

    private String language;

    protected Language(){}

    public Language(final String language) {
        this.language = language;
    }

}