package ru.geekbrains.aprilmarket.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;
import ru.geekbrains.aprilmarket.entities.User;

@Getter
@Setter
public class OnEmailVerificationCompleteEvent extends ApplicationEvent {
    private User user;
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param user the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public OnEmailVerificationCompleteEvent(User user) {
        super(user);
        this.user = user;
    }
}
