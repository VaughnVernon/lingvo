// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.example.domain.model;

/**
 * Equivalent to Invalid Tax Payer ID
 */
public class ExcepcionDeRfcInvalido extends RuntimeException {

    public ExcepcionDeRfcInvalido(final String message) {
        super(message);
    }
}
