package com.mystic.mdn;

import java.util.ArrayList;

/**
 * This class represent a parameter of the {@link MDNElement}
 */
public class MDNParam {

    private String name;

    private final ArrayList<String> values = new ArrayList<>();

    /**
     * Constructor for {@link MDNParam}
     * @param name the name of this parameter
     */
    public MDNParam(String name) {
        this.name = name;
    }

    /**
     * Get the name of this parameter
     * @return the name of this parameter
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name for this parameter
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add a value to {@link MDNParam#values}
     * @param value the value you want to add
     * @return this
     */
    public MDNParam addValue(String value) {
        values.add(value);
        return this;
    }

    /**
     * @return {@code true} if this parameter doesn't contains values otherwise {@code false}
     */
    public boolean isEmpty() {
        return values.size() == 0;
    }

    /**
     * @return {@code true} is this parameter contains more than 1 value otherwise {@code false}
     */
    public boolean isList() {
        return values.size() > 1;
    }

    /**
     * @return the number of value in {@link #values}
     */
    public int getValueCount() {
        return values.size();
    }

    /**
     * @return the list of values
     */
    public ArrayList<String> getValues() {
        return values;
    }

    /**
     * Return the the value at the matching {@code index} or {@code defaultValue} if none was found
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public String getValue(int index, String defaultValue) {
        try {
            return values.get(index);
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Int version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Integer getIntValue(int index, Integer defaultValue) {
        try {
            return Integer.parseInt(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Long version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Long getLongValue(int index, Long defaultValue) {
        try {
            return Long.parseLong(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Hexadecimal version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Long getHexValue(int index, Long defaultValue) {
        try {
            return Long.parseLong(values.get(index), 16);
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Binary version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Long getBinValue(int index, Long defaultValue) {
        try {
            return Long.parseLong(values.get(index), 2);
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Float version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Float getFloatValue(int index, Float defaultValue) {
        try {
            return Float.parseFloat(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Double version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Double getDoubleValue(int index, Double defaultValue) {
        try {
            return Double.parseDouble(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Short version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Short getShortValue(int index, Short defaultValue) {
        try {
            return Short.parseShort(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Byte version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Byte getByteValue(int index, Byte defaultValue) {
        try {
            return Byte.parseByte(values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Character version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @return the value at matching {@code index} or {@code defaultValue}
     */
    public Character getCharValue(int index, Character defaultValue) {
        try {
            return values.get(index).charAt(0);
        } catch (Exception any) {
            return defaultValue;
        }
    }

    /**
     * Enum version of {@link #getValue(int, String)}
     * @param index the index of the value you are looking for
     * @param defaultValue the default value if there is no matching value
     * @param enumClass the class of the resulting enum
     * @param <E> generic of the resulting enum
     * @return the value matching {@code index} or {@code defaultValue}
     */
    public <E extends Enum<E>> E getEnumValue(int index, E defaultValue, Class<E> enumClass) {
        try {
            return Enum.valueOf(enumClass, values.get(index));
        } catch (Exception any) {
            return defaultValue;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append(": ");
        if(isEmpty()) builder.append("\"\"");
        else if(!isList()) builder.append("\"").append(values.get(0)).append("\"");
        else {
            builder.append("[");
            int i = 0;
            for(String str : values) {
                if(i != 0) builder.append(",");
                builder.append("\"").append(str).append("\"");
                i++;
            }
            builder.append("]");
        }
        return builder.toString();
    }
}
