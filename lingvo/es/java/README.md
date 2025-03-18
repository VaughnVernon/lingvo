# Lingvo: DDD en Español para Java

Expresa tu dominio en español, con fluidez y claridad.

## ¿Qué es?

Lingvo es una biblioteca Java que transforma la programación orientada a dominios (DDD) en una experiencia nativa en español. Reemplaza estructuras de control como `if`, `switch`, y `try` con equivalentes fluidos y expresivos como `si`, `coincide`, y `intenta`. Diseñada para alinear el código con el Lenguaje Ubicuo de tu dominio, Lingvo elimina la brecha entre ingenieros y expertos del dominio que hablan español.

## Ejemplo

```java
import static tools.dddesign.lingvo.es.java.Sentencia.*;

class Ejemplo {
  public void validarEdad(int edad) {
    coincide(edad)
      .con(18, () -> System.out.println("Mayor de edad"))
      .conYContinua(0, () -> System.out.println("Recién nacido"))
      .nones(() -> arroja(nueva(IllegalArgumentException.class, "Edad inválida: " + edad)));
  }
}
```

## ¿Por qué Lingvo?

- **Fluidez**: Escribe código que se lee como una narrativa en español.
- **DDD Puro**: Refleja el lenguaje Ubicuo directamente en el código.
- **Colaboración**: Facilita la comunicación entre equipos técnicos y no técnicos en español.
- **Practicidad**: Construido con TDD, probado en casos reales como RFC y Contribuyentes.

## Funcionalidades

- `si` / `oSi` / `siNo`: Condicionales naturales.
- `coincide` / `con` / `conYContinua` / `nones`: Alternativa a switch.
- `bucle` / `porCada`: Iteraciones simples y expresivas.
- `intenta` / `atrapa` / `finalmente`: Manejo de excepciones elegante.
- `arroja` / `nueva`: Creación y lanzamiento de excepciones.
- `estaPresente` / `obtener`: Soporte para Optionals y Colecciones.

### Ejemplo real

```java
class Contribuyente {
  public void designarRepresentanteLegal(RFC rfc, NombreCompleto nombre) {
    coincide(rfc.tipoDePersona())
      .con(TipoDePersona.FISICA, () -> arroja(nueva(ExcepcionOperacionNoPermitida.class, "No permitido")))
      .con(TipoDePersona.MORAL, () -> this.representanteLegal = RepresentanteLegal.con(rfc, nombre))
      .nones(() -> arroja(nueva(ExcepcionTipoPersonaInvalido.class, "Tipo inválido")));
  }
}
```

## ¿Cómo contribuir?

¿Ideas para más funciones en español? ¿Encontraste un bug? ¡Abre un issue o un PR! Colaboramos con Vaughn Vernon para hacer de Lingvo una herramienta viva para la comunidad DDD.

## License

Licencia Mozilla Pública 2.0 - © 2024-2025 Vaughn Vernon y Gustavo Vargas.

## Agradecimientos

Creado por Gustavo Vargas (@gusvmx) con la guía de Vaughn Vernon (@VaughnVernon). ¡Gracias por inspirar este viaje!

---

# Lingvo: DDD in Spanish for Java

Express your domain in Spanish, with flair and finesse.

## What’s This All About?

Lingvo is a Java library that turns Domain-Driven Design (DDD) into a Spanish-speaking adventure. Say adiós to clunky if, switch, and try—hello to smooth, expressive buddies like si, coincide, and intenta. Built to glue your code to your domain’s Ubiquitous Language, Lingvo bridges the gap between Spanish-speaking engineers and domain experts faster than you can say "¡Hola!"

## A Taste of It
```java
import static tools.dddesign.lingvo.es.java.Sentencia.*;

class Example {
  public void checkAge(int age) {
    coincide(age)
      .con(18, () -> System.out.println("Officially too old for kid’s menus"))
      .conYContinua(0, () -> System.out.println("Fresh out of the oven!"))
      .nones(() -> arroja(nueva(IllegalArgumentException.class, "Age? What age? " + age)));
  }
}
```

## Why Lingvo?

Fluency: Write code that reads like a Spanish novella—minus the drama, unless you want it.
DDD Done Right: Mirrors your Ubiquitous Language so well, it’s practically a domain expert itself.
Teamwork: Makes techies and non-techies chat in Spanish without a translator app.
Real Deal: Forged in the fires of TDD, battle-tested with RFCs and Taxpayers.

## What’s in the Toolbox?

* si / oSi / siNo: Conditionals that flow like a good salsa beat.
* coincide / con / conYContinua / nones: A switch statement that’s basically a Spanish dance move. 
* bucle / porCada: Loops so simple, even your abuela could follow. 
* intenta / atrapa / finalmente: Exception handling with style and grace. 
* arroja / nueva: Throw and craft exceptions like a pro. 
* estaPresente / obtener: Optionals and Collections, Spanish-style.

### Real-World Swagger
```java
class TaxPayer {
  public void assignLegalRep(RFC rfc, FullName name) {
    coincide(rfc.personType())
      .con(PersonType.FISICA, () -> arroja(nueva(OperationNotAllowedException.class, "Nope, not for individuals")))
      .con(PersonType.MORAL, () -> this.legalRep = LegalRep.con(rfc, name))
      .nones(() -> arroja(nueva(InvalidPersonTypeException.class, "What even is this type?")));
  }
}
```

## Wanna Jump In?

Got ideas for more Spanish goodies? Spot a bug? Toss us an issue or a PR! We’re teaming up with Vaughn Vernon to keep Lingvo a living, breathing tool for the DDD crew.

## License

Mozilla Public License 2.0 - © 2024-2025 Vaughn Vernon and Gustavo Vargas.

## High-Fives

Whipped up by Gustavo Vargas (@gusvmx) with a big assist from Vaughn Vernon (@VaughnVernon). Thanks for sparking this wild ride!