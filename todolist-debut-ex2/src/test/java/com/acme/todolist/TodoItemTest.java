package com.acme.todolist.domain;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoItemTest {

    @Test
    void shouldNotMarkItemAsLateIfLessThan24HoursOld() {
        TodoItem item = new TodoItem("1", Instant.now().minus(23, ChronoUnit.HOURS), "Content");
        assertEquals("Content", item.finalContent());
    }

    @Test
    void shouldMarkItemAsLateIfExactly24HoursOld() {
        TodoItem item = new TodoItem("2", Instant.now().minus(24, ChronoUnit.HOURS).minus(1, ChronoUnit.MINUTES), "Content");
        assertEquals("[LATE!] Content", item.finalContent());
    }

    @Test
    void shouldMarkItemAsLateIfMoreThan24HoursOld() {
        TodoItem item = new TodoItem("3", Instant.now().minus(25, ChronoUnit.HOURS), "Content");
        assertEquals("[LATE!] Content", item.finalContent());
    }
}
