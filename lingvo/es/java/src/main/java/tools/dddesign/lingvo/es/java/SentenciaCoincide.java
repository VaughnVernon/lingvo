// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java;

import jakarta.annotation.Nonnull;

/*
 * switch - coincide
 * case - con
 * case without break - con sin terminar
 * default - nones
 */
public class SentenciaCoincide<T> {

    private final T variable;
    private final boolean terminado;

    private SentenciaCoincide(@Nonnull final T variable) {
        this.variable = variable;
        this.terminado = false;
    }

    private SentenciaCoincide(final T variable, final boolean terminado) {
        this.variable = variable;
        this.terminado = terminado;
    }

    public static <T> SentenciaCoincide<T> coincide(@Nonnull final T variable) {
        return new SentenciaCoincide<>(variable);
    }

    public SentenciaCoincide<T> con(@Nonnull final T valor, @Nonnull final Runnable ejecutable) {
        if (terminado) {
            return this;
        }

        boolean ejecutado = false;
        if (variable.equals(valor)) {
            ejecutable.run();
            ejecutado = true;
        }
        return new SentenciaCoincide<>(this.variable, ejecutado);
    }

    public SentenciaCoincide<T> conSinTerminar(@Nonnull final T valor, @Nonnull final Runnable ejecutable) {
        if (terminado) {
            return this;
        }
        con(valor, ejecutable);
        return this;
    }

    public void nones(@Nonnull final Runnable ejecutable) {
        if (!terminado) {
            ejecutable.run();
        }
    }
}
