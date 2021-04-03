package com.mystic.mdn;

import java.util.ArrayList;

/**
 * This class represent an element of the {@link MDNDocument}
 */
public class MDNElement {

    private String name;

    private final ArrayList<MDNParam> params = new ArrayList<>();

    private final ArrayList<MDNElement> elements = new ArrayList<>();

    /**
     * Constructor for MDNElement
     * @param name the name of the element
     */
    public MDNElement(String name) {
        this.name = name;
    }

    /**
     * Get the name of the element
     * @return the name of the element
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name for the element
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add an element to the {@link MDNElement#elements}
     * @param element the element you want to add to the {@link MDNElement#elements list}
     * @return this
     */
    public MDNElement addElement(MDNElement element) {
        elements.add(element);
        return this;
    }

    /**
     * Remove the element with the matching name from the {@link MDNElement#elements}
     * @param name the name of the element you want to remove
     */
    public void removeElement(String name) {
        MDNElement toRemove = null;

        for(MDNElement element : elements) {
            if(element.getName().equals(name)) {
                toRemove = element;
                break;
            }
        }

        if(toRemove != null) elements.remove(toRemove);
    }

    /**
     * Add a param to the {@link MDNElement#params}
     * @param param the param you want to add to the {@link MDNElement#params list}
     * @return this
     */
    public MDNElement addParam(MDNParam param) {
        params.add(param);
        return this;
    }

    /**
     * Remove the param with the matching name from the {@link MDNElement#params}
     * @param name the name of the param you want to remove
     */
    public void removeParam(String name) {
        MDNParam toRemove = null;

        for(MDNParam param : params) {
            if(param.getName().equals(name)) {
                toRemove = param;
                break;
            }
        }

        if(toRemove != null) params.remove(toRemove);
    }

    /**
     * Get the first element with the matching <code>name</code>
     * @param name the name of the element you are looking for
     * @return the element found or null
     */
    public MDNElement getElementByName(String name) {
        for(MDNElement element : elements) {
            if(element.name.equals(name)) return element;
        }
        return null;
    }

    /**
     * Get the list of elements with the matching <code>name</code> as an array
     * @param name the name of the elements you are looking for
     * @return all the matching elements
     */
    public MDNElement[] getElementsByName(String name) {
        ArrayList<MDNElement> elements = new ArrayList<>();
        for(MDNElement element : this.elements) {
            if(element.name.equals(name)) elements.add(element);
        }
        MDNElement[] arr = new MDNElement[elements.size()];
        arr = elements.toArray(arr);
        return arr;
    }

    /**
     * Get the first param with the matching <code>name</code>
     * @param name the name of the param you are looking for
     * @return the param found or null
     */
    public MDNParam getParamByName(String name) {
        for(MDNParam param : params) {
            if(param.getName().equals(name)) return param;
        }
        return null;
    }

    /**
     * Get the list of params with the matching <code>name</code> as an array
     * @param name the name of the params you are looking for
     * @return all the matching params
     */
    public MDNParam[] getParamsByName(String name) {
        ArrayList<MDNParam> params = new ArrayList<>();
        for(MDNParam param : this.params) {
            if(param.getName().equals(name)) params.add(param);
        }
        MDNParam[] arr = new MDNParam[params.size()];
        arr = params.toArray(arr);
        return arr;
    }

    /**
     * Return the list of elements
     * @return the list of elements
     */
    public ArrayList<MDNElement> getElements() {
        return elements;
    }

    /**
     * Return the list of parameters
     * @return the list of parameters
     */
    public ArrayList<MDNParam> getParams() {
        return params;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(":");
        for(MDNParam param : params) {
            builder.append("\n").append('\t').append(param.toString());
        }
        for(MDNElement element : elements) {
            for(String line : element.toString().split("\n")) {
                builder.append("\n").append("\t").append(line);
            }
        }
        return builder.toString();
    }
}
