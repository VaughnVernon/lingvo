// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.acceptance.domain.model;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * Full name
 */
public class NombreCompleto {

    /** First name */
    private String nombre;
    /** Surname */
    private String segundoNombre;
    /** Last name */
    private String apellidoPaterno;
    /** Second last name */
    private String apellidoMaterno;

    protected NombreCompleto() { }

    private NombreCompleto(final String nombre, final String apellidoPaterno) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
    }

    private NombreCompleto(final NombreCompleto nombreOriginal) {
        this.nombre = nombreOriginal.nombre;
        this.segundoNombre = nombreOriginal.segundoNombre;
        this.apellidoPaterno = nombreOriginal.apellidoPaterno;
        this.apellidoMaterno = nombreOriginal.apellidoMaterno;
    }

    public static NombreCompleto de(@Nonnull final String nombre, @Nonnull final String apellidoPaterno) {
        return new NombreCompleto(nombre, apellidoPaterno);
    }

    public NombreCompleto conSegundoNombre(@Nullable final String segundoNombre) {
        if (segundoNombre == null) {
            return this;
        }

        NombreCompleto nuevoNombre = new NombreCompleto(this);
        nuevoNombre.segundoNombre = segundoNombre;

        return nuevoNombre;
    }

    public NombreCompleto conApellidoMaterno(@Nullable final String apellidoMaterno) {
        if (apellidoMaterno == null) {
            return this;
        }
        NombreCompleto nuevoNombre = new NombreCompleto(this);
        nuevoNombre.apellidoMaterno = apellidoMaterno;

        return nuevoNombre;
    }
}
