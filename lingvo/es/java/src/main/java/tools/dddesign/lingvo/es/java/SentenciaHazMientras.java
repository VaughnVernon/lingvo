// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import jakarta.annotation.Nonnull;

import java.util.function.BooleanSupplier;

/*
 * do - haz
 * while - mientras que
 */
public class SentenciaHazMientras {

    private final Runnable ejecutable;

    private SentenciaHazMientras(final Runnable ejecutable) {
        this.ejecutable = ejecutable;
    }

    public static SentenciaHazMientras haz(@Nonnull final Runnable ejecutable) {
        ejecutable.run();
        return new SentenciaHazMientras(ejecutable);
    }

    public void mientrasQue(@Nonnull final BooleanSupplier condicion) {
        while (condicion.getAsBoolean()) {
            ejecutable.run();
        }
    }
}
