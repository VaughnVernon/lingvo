// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.jupiter.api.Assertions.*;
import static tools.dddesign.lingvo.es.java.SentenciaHazMientras.haz;

class SentenciaHazMientrasTest {

    @Test
    void hazMientrasQueFalsoEjecutaAlMenos1Vez() {
        AtomicLong actual = new AtomicLong();

        haz(actual::incrementAndGet)
                .mientrasQue(() -> false);

        assertEquals(1L, actual.get());
    }

    @Test
    void hazMientrasQueActualSea10() {
        AtomicLong actual = new AtomicLong();

        haz(actual::incrementAndGet)
        .mientrasQue(() -> actual.get() < 10);

        assertEquals(10L, actual.get());
    }
}