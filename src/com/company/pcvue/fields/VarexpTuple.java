package com.company.pcvue.fields;

/**
 * This is a tuple class created solely for PcVue Variables that needs to be implemented with a combo box or a textfield.
 * This class takes and stores a varexp array position (int), text to display to the user and PcVue options
 * <p>
 * Note that while this is generic, the meaning for each paramemters differ greatly if it is implemented for a textfield
 * item or a combobox item
 * <p>
 * position: This is the varexp array position
 * <p>
 * userText: This will be the choices displayed to the user as a list of Strings (Combobox) or the default display text as a string (TextField).
 * <p>
 * pcVueWriteOptions: This will be the choices that will write to PcVue or a limitation on a particular field. This MUST match the exact format as the userText.
 * This is a list of Strings (Combobox), but this should be the value limit for text fields. However, it can also be blank (also for TextField)
 *
 * visibility: This will determine if an item shows up on the GUI. The reason this exists is because certain items should never show up (ex: reserved tags)
 * and some item shows up only if a specific choice is made in another field
 */
public class VarexpTuple<Position, WidgetType, UserDisplayText, PcVueWriteOptions, Visibility> {
    public final Position position;
    public final UserDisplayText userText;
    public final WidgetType widgetType;
    public final PcVueWriteOptions pcVueWriteOptions;
    public final Visibility visibility;

    public VarexpTuple(Position position, WidgetType widgetType, UserDisplayText userText, PcVueWriteOptions pcVueWriteOptions, Visibility visibility) {
        this.position = position;
        this.userText = userText;
        this.widgetType = widgetType;
        this.pcVueWriteOptions = pcVueWriteOptions;
        this.visibility = visibility;
    }

    public Position getPosition() {
        return position;
    }

    public WidgetType getWidgetType() {
        return widgetType;
    }

    public UserDisplayText getUserText() {
        return userText;
    }

    public PcVueWriteOptions getPcVueWriteOptions() {
        return pcVueWriteOptions;
    }

    public Visibility getVisibility() {
        return visibility;
    }
}
