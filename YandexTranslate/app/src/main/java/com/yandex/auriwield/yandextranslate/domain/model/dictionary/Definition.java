
package com.yandex.auriwield.yandextranslate.domain.model.dictionary;

import java.util.List;

public class Definition {

    private String speechPart;
    private List<DefinitionPart> definitionParts;

    public Definition( String speechPart, List<DefinitionPart> definitionParts) {
        this.speechPart = speechPart;;
        this.definitionParts = definitionParts;
    }

    public String getSpeechPart() {
        return speechPart;
    }

    public List<DefinitionPart> getDefinitionParts() {
        return definitionParts;
    }
}
