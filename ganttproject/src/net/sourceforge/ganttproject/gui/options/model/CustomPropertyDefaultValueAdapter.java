package net.sourceforge.ganttproject.gui.options.model;

import java.util.Date;

import net.sourceforge.ganttproject.CustomPropertyDefinition;

public abstract class CustomPropertyDefaultValueAdapter {
    public static GPOption createDefaultValueOption(final CustomPropertyDefinition def) {
        switch (def.getPropertyClass()) {
        case TEXT:
            class TextDefaultValue extends DefaultStringOption {
                TextDefaultValue() {
                    super("customPropertyDialog.defaultValue.text", def.getDefaultValueAsString());
                }
                @Override
                public void setValue(String value) {
                    super.setValue(value);
                    if (isChanged()) {
                        def.setDefaultValueAsString(value);
                    }
                }
            }
            return new TextDefaultValue();
        case BOOLEAN:
            class BooleanDefaultValue extends DefaultBooleanOption {
                BooleanDefaultValue() {
                    super("customPropertyDialog.defaultValue.boolean", (Boolean)def.getDefaultValue());
                }
                @Override
                public void setValue(Boolean value) {
                    super.setValue(value);
                    if (isChanged()) {
                        def.setDefaultValueAsString(String.valueOf(value));
                    }
                }
            }
            return new BooleanDefaultValue();
        case INTEGER:
            class IntegerDefaultValue extends DefaultIntegerOption {
                IntegerDefaultValue() {
                    super("customPropertyDialog.defaultValue.integer", (Integer)def.getDefaultValue());
                }
                @Override
                public void setValue(int value) {
                    super.setValue(value);
                    if (isChanged()) {
                        def.setDefaultValueAsString(String.valueOf(value));
                    }
                }
            }
            return new IntegerDefaultValue();
        case DOUBLE:
            class DoubleDefaultValue extends DefaultDoubleOption {
                DoubleDefaultValue() {
                    super("customPropertyDialog.defaultValue.double", (Double)def.getDefaultValue());
                }
                @Override
                public void setValue(Double value) {
                    super.setValue(value);
                    if (isChanged()) {
                        def.setDefaultValueAsString(String.valueOf(value));
                    }
                }
            }
            return new DoubleDefaultValue();
        case DATE:
            class DateDefaultValue extends DefaultDateOption {
                DateDefaultValue() {
                    super("customPropertyDialog.defaultValue.date", (Date)def.getDefaultValue());
                }
                @Override
                public void setValue(Date value) {
                    super.setValue(value);
                    if (isChanged()) {
                        def.setDefaultValueAsString(String.valueOf(value));
                    }
                }
            }
            return new DateDefaultValue();
        default:
            return null;
        }
    }
}