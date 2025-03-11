// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;

class SentenciaTest {

    @Test
    void si() {
        AtomicLong counter = new AtomicLong();

        Sentencia.si(true, counter::incrementAndGet);

        assertEquals(1L, counter.get());
    }

    @Test
    void coincide() {
        AtomicLong counter = new AtomicLong();

        Sentencia.coincide(counter.get())
                .con(0L,  counter::incrementAndGet)
                .nones(Assertions::fail);

        assertEquals(1L, counter.get());
    }

    @Test
    void bucle() {
        AtomicLong i = new AtomicLong();
        AtomicLong counter = new AtomicLong();

        Sentencia.bucle(() -> i.set(0), () -> i.get() < 10, () -> i.incrementAndGet(), () -> {
            counter.incrementAndGet();
        });

        assertEquals(10L, counter.get());
    }

    @Test
    void mientras() {
        AtomicLong counter = new AtomicLong();

        Sentencia.mientras(() -> counter.get() < 10, () -> {
            counter.incrementAndGet();
        });

        assertEquals(10L, counter.get());
    }

    @Test
    void hazMientrasQue() {
        AtomicLong counter = new AtomicLong();

        Sentencia.haz(counter::incrementAndGet)
                .mientrasQue(() -> counter.get() < 10);

        assertEquals(10L, counter.get());
    }

    @Test
    void porCada() {
        List<Long> valores = List.of(1L, 2L, 3L);
        AtomicLong counter = new AtomicLong();

        Sentencia.porCada(valores, counter::addAndGet);

        assertEquals(6L, counter.get());
    }

    @Test
    void intenta() {
        AtomicLong counter = new AtomicLong();

        Sentencia.intenta(counter::incrementAndGet)
                .finalmente(counter::incrementAndGet);

        assertEquals(2L, counter.get());
    }



}