package seedu.foodiebot.commons.core;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

class MessagesTest {
    @Test
    public void messages_notBlank() {
        boolean value = true;
        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                if (field.get(this) != null) {
                    value = false;
                }
            } catch (Exception e) {
                System.out.println("Exception occurred in processing");
            }
        }
        assertFalse(value);
    }
}
