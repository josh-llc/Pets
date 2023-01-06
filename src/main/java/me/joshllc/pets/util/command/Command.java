package me.joshllc.pets.util.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    String name();
    String permission() default "";
    String noPermission() default "&cNo permission.";
    String[] aliases() default {};
    String description() default "";
    String usage() default "";
    boolean inGameOnly() default false;
}
