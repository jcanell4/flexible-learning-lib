package org.elsquatrecaps.thymeleaf;

import java.util.HashSet;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.util.Set;

/**
 *
 * @author josep
 */
public class ThymeLeafTemplate{
    String name;
    String fragment=null;
    private Context context;
    private TemplateEngine templateEngine;

    public ThymeLeafTemplate(String name, TemplateEngine templateEngine) {
        context = new Context();
        this.name = name;
        this.templateEngine = templateEngine;
    }

    public ThymeLeafTemplate(String name, String fragment, TemplateEngine templateEngine) {
        context = new Context();
        this.name = name;
        this.fragment = fragment;
        this.templateEngine = templateEngine;
    }

    public void addObject(String varName, Object value){
        context.setVariable(varName, value);
    }

    public String buildContent() {
        Set<String> fragmentsSelectors = new HashSet<>();
        fragmentsSelectors.add(fragment);
        return templateEngine.process(name,fragmentsSelectors, context);
    }
}


