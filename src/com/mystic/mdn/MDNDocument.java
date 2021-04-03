package com.mystic.mdn;

import java.util.ArrayList;

/**
 * This class is used to store the content of an MDN file, so it's easier to access data. <br>
 * It contains a list of element {@link MDNDocument#elements}. <br>
 * You can add and remove elements from it. <br>
 * <br>
 * Format:<br>
 * <pre>
 * # This is a comment
 * &lt;Element[param1(125), param2(Hello)]&gt;
 *      &lt;Sub-Element[param1(Value1)]/&gt;
 *      &lt;Sub-Element[param2(Value2)]/&gt;
 * &lt;/&gt;
 * </pre>
 */
public class MDNDocument {

    private final ArrayList<MDNElement> elements = new ArrayList<>();

    /**
     * This function is used to add an element to the {@link MDNDocument#elements list}
     * @param element the element you want to add to the {@link MDNDocument#elements list}
     */
    public void addElement(MDNElement element) {
        elements.add(element);
    }

    /**
     * This function is used to remove an element from the {@link MDNDocument#elements list}
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
     * This function is used to get the list of elements
     * @return the {@link MDNDocument#elements list of elements}
     */
    public ArrayList<MDNElement> getElements() {
        return elements;
    }

    /**
     * This function is used to get all the elements that have the same name as <code>name</code>
     * @param name the name of the elements you are looking for
     * @return an array containing all the matching elements
     */
    public MDNElement[] getElementsByName(String name) {
        ArrayList<MDNElement> list = new ArrayList<>();

        for(MDNElement element : elements) {
            if(element.getName().equals(name)) list.add(element);
        }

        MDNElement[] arr = new MDNElement[list.size()];
        arr = list.toArray(arr);
        return arr;
    }

    /**
     * This function is used to get the first element that have the same name as <code>name</code>
     * @param name the name of the element you are looking for
     * @return the element found or null if there is no matching elements
     */
    public MDNElement getElementByName(String name) {
        for(MDNElement element : elements) {
            if(element.getName().equals(name)) return element;
        }
        return null;
    }

}