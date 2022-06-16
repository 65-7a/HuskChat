package net.william278.huskchat.velocity.event;

import net.william278.huskchat.event.IBroadcastMessageEvent;
import org.jetbrains.annotations.NotNull;

public class BroadcastMessageEvent extends VelocityEvent implements IBroadcastMessageEvent {
    private String message;

    public BroadcastMessageEvent(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(@NotNull String message) {
        this.message = message;
    }
}
