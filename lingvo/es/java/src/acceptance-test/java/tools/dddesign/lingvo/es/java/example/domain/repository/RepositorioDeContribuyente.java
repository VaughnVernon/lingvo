// Copyright © 2024-2025 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.
package tools.dddesign.lingvo.es.java.example.domain.repository;

import tools.dddesign.lingvo.es.java.example.domain.model.Contribuyente;
import tools.dddesign.lingvo.es.java.example.domain.model.RFC;

import java.util.Optional;

/**
 * Tax Payer Repository
 */
public interface RepositorioDeContribuyente {

    Optional<Contribuyente> encontrarPorRfc(RFC rfc);

    void almacenar(Contribuyente contribuyente);
}
