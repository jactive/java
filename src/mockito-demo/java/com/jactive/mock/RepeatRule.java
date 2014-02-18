package com.jactive.mock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * See http://goo.gl/6qutww
 */
public class RepeatRule implements TestRule {

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Repeat {
        int value() default 1;
    }

    private static class RepeatStatement extends Statement {

        private final int times;
        private final Statement statement;

        private RepeatStatement(int times, Statement statement) {
            this.times = times;
            this.statement = statement;
        }

        public void evaluate() throws Throwable {
            for (int i = 0; i < times; i++) {
                statement.evaluate();
            }
        }
    }

    public Statement apply(Statement statement, Description description) {
        Statement result = statement;
        Repeat repeat = description.getAnnotation(Repeat.class);
        if (repeat != null) {
            int times = repeat.value();
            result = new RepeatStatement(times, statement);
        }
        return result;
    }
}
