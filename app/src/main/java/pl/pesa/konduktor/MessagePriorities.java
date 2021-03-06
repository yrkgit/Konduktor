package pl.pesa.konduktor;

import androidx.annotation.NonNull;

public enum MessagePriorities {

    CRITICAL ("#FF8000"),
    HIGH("#FF8000"),
    MEDIUM("#FF8000"),
    LOW("#FF8000"),
    INFORM("#FF8000");

    private final String messageBoxColor;

    MessagePriorities(final String messageBoxColor){
        this.messageBoxColor= messageBoxColor;
    }

    @NonNull
    @Override
    public String toString() {
        return messageBoxColor;
    }
}
