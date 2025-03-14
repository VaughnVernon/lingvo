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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static tools.dddesign.lingvo.es.java.SentenciaIntenta.intenta;

class SentenciaIntentaTest {

    @Test
    void intentaSinExcepcionNiFinalmente() {
        //Given
        AtomicLong counter = new AtomicLong();

        //When
        intenta(counter::incrementAndGet)
                .atrapa(RuntimeException.class, Assertions::fail)
                .ahora();

        //Then
        assertEquals(1L, counter.get());
    }

    @Test
    void intentaSinExcepcionConFinalmente() {
        //Given
        AtomicLong counter = new AtomicLong();

        //When
        intenta(counter::incrementAndGet)
                .atrapa(RuntimeException.class, Assertions::fail)
                .finalmente(counter::incrementAndGet);

        //Then
        assertEquals(2L, counter.get());
    }

    @Test
    void intentaConExcepcionSinAtraparConFinamente_arrojaExcepcion() {
        AtomicLong counter = new AtomicLong();

        IllegalArgumentException actualEx = assertThrows(IllegalArgumentException.class, () ->
                intenta(() -> {
                    throw new IllegalArgumentException("Arrojada a propósito");
                }).finalmente(counter::incrementAndGet));

        Assertions.assertTrue(actualEx.getMessage().equals("Arrojada a propósito"));
        assertEquals(1L, counter.get());
    }

    @Test
    void intentaConExcepcionAtrapadaSinFinalmente() {
        AtomicLong counter = new AtomicLong();

        intenta(() -> {
            throw new IllegalArgumentException();
        }).atrapa(IllegalArgumentException.class, e -> {
            counter.incrementAndGet();
        }).ahora();

        assertEquals(1L, counter.get());
    }

    @Test
    void intentaConExcepcionAtrapadaConFinalmente() {
        AtomicLong counter = new AtomicLong();

        intenta(() -> {
            throw new IllegalArgumentException();
        }).atrapa(IllegalArgumentException.class, e -> {
            counter.incrementAndGet();
        }).finalmente(counter::incrementAndGet);

        assertEquals(2L, counter.get());
    }

    @Test
    void intentaConExcepcionNoAtrapadaPorSerDistintoTipoConFinalmente() {
        AtomicLong counter = new AtomicLong();

        assertThrows(IllegalArgumentException.class, () ->
                intenta(() -> {
                    throw new IllegalArgumentException();
                }).atrapa(IllegalStateException.class, e -> {
                    fail();
                }).finalmente(counter::incrementAndGet));

        assertEquals(1L, counter.get());
    }

    @Test
    void intentaSinAtraparSinFinalmenteArrojaIllegalArgument() {
        AtomicLong counter = new AtomicLong();

        IllegalArgumentException actualEx = assertThrows(IllegalArgumentException.class, () ->
                intenta(counter::incrementAndGet)
                        .ahora());

        assertEquals("Es necesario atrapar una excepción o proporcionar un ejecutable al final",  actualEx.getMessage());
    }
}