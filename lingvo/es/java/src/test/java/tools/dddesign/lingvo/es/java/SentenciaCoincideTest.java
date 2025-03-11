// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import static tools.dddesign.lingvo.es.java.SentenciaCoincide.coincide;

class SentenciaCoincideTest {

    @Test
    void coincideConTerminaInmediatamente() {
        //Given
        AtomicLong resultado = new AtomicLong();
        String variable = "variable";

        //When
        coincide(variable)
                .con("variable", resultado::incrementAndGet)
                .con("otro valor", Assertions::fail)
                .conSinTerminar("valor", Assertions::fail)
                .nones(Assertions::fail);

        //Then
        assertEquals(1, resultado.get());
    }

    @Test
    void coincideConSinTerminarEjecutaNones() {
        AtomicLong resultado = new AtomicLong();
        String variable = "variable";

        coincide(variable)
                .conSinTerminar("variable", resultado::incrementAndGet)
                .nones(resultado::incrementAndGet);

        assertEquals(2, resultado.get());
    }

    @Test
    void noCoincideEjecutaNones() {
        AtomicLong resultado = new AtomicLong();
        String variable = "variable";

        coincide(variable)
                .con("otro valor", Assertions::fail)
                .nones(resultado::incrementAndGet);

        assertEquals(1, resultado.get());
    }

    @Test
    void noCoincideConSinTerminarEjecutaNones() {
        AtomicLong resultado = new AtomicLong();
        String variable = "variable";

        coincide(variable)
                .conSinTerminar("no coincide", Assertions::fail)
                .nones(resultado::incrementAndGet);

        assertEquals(1, resultado.get());
    }
}