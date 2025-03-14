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

import static tools.dddesign.lingvo.es.java.SentenciaSi.si;

class SentenciaSiTest {

    public static final boolean FALSO = false;
    public static final boolean VERDADERO = true;

    @Test
    void siVerdadero() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(VERDADERO, resultado::incrementAndGet);

        //Then
        Assertions.assertEquals(1, resultado.get());
    }

    @Test
    void siFalso() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(FALSO, Assertions::fail);

        //Then
        Assertions.assertEquals(0, resultado.get());
    }

    @Test
    void oSiVerdadero() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(FALSO, Assertions::fail)
                .oSi(VERDADERO, resultado::incrementAndGet);
    }

    @Test
    void oSiFalso() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(FALSO, Assertions::fail)
                .oSi(FALSO, Assertions::fail);

        //Then
        Assertions.assertEquals(0, resultado.get());
    }

    @Test
    void siNo() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(FALSO, Assertions::fail)
                .siNo(resultado::incrementAndGet);

        //Then
        Assertions.assertEquals(1, resultado.get());
    }

    @Test
    void siVerdaderoOSiNoSeEjecutaSiNoNoSeEjecuta() {
        //Given
        AtomicLong resultado = new AtomicLong();

        //When
        si(VERDADERO, resultado::incrementAndGet)
                .oSi(VERDADERO, Assertions::fail)
                .siNo(Assertions::fail);

        //Then
        Assertions.assertEquals(1, resultado.get());
    }
}