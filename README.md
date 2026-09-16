# TP 15 - Expressions Lambda

Cours : Fondamentaux et Concepts Avancés de la Programmation Java

Ce TP contient 17 étapes progressives sur les expressions lambda en Java 8+ : syntaxe de base, interfaces fonctionnelles standard et primitives, références de méthodes, API Stream, composition de fonctions, gestion des exceptions, et applications pratiques (validation, comptage de TODO, factorielle, memoization).

## Objectifs

- Comprendre les expressions lambda comme fonctions anonymes traitant le code comme des données
- Utiliser les interfaces fonctionnelles standard (Predicate, Consumer, Function, Supplier) et leurs variantes primitives
- Maîtriser les différentes syntaxes de lambda et la capture de variables
- Utiliser les références de méthodes (statique, instance, constructeur)
- Exploiter l'API Stream pour des traitements déclaratifs sur des collections et des fichiers
- Composer des fonctions (andThen, compose, and, or, negate)
- Gérer les exceptions dans un contexte fonctionnel
- Appliquer les lambdas à des cas concrets : validation, comparateur composé, factorielle, memoization

## Prérequis

- JDK 17 ou supérieur
- Un IDE (Eclipse, IntelliJ, VS Code) ou un terminal avec `javac` et `java`

## Compilation et exécution

Chaque étape correspond à une classe autonome avec sa propre méthode `main` (package par défaut).

```bash
javac LambdaIntro.java
java LambdaIntro
```

## Étape 1 : Comprendre le concept de base

Créer un premier projet Java et une classe de test simple pour s'assurer que l'environnement fonctionne.

```java
public class LambdaIntro {
    public static void main(String[] args) {
        System.out.println("Bienvenue dans le TP sur les expressions lambda!");
    }
}
```

Classe : `LambdaIntro.java`

## Étape 2 : Première expression lambda simple

Créer une interface fonctionnelle `Calculateur` et comparer son implémentation via classe anonyme et via lambda.

```java
@FunctionalInterface
interface Calculateur {
    int calculer(int a, int b);
}

Calculateur additionLambda = (a, b) -> a + b;
```

Classe : `LambdaIntro.java`

## Étape 3 : Interfaces du package java.util.function

Utiliser les interfaces fonctionnelles standard : `Predicate`, `Consumer`, `Function`, `Supplier`.

```java
Predicate<String> estVide = s -> s.isEmpty();
Consumer<String> afficheur = s -> System.out.println("Affichage: " + s);
Function<String, Integer> longueur = s -> s.length();
Supplier<Double> nombreAleatoire = () -> Math.random();
```

Classe : `InterfacesFonctionnelles.java`

## Étape 4 : Interfaces fonctionnelles pour types primitifs

Utiliser `IntPredicate`, `IntConsumer`, `IntFunction`, `IntSupplier` pour éviter le boxing/unboxing.

```java
IntPredicate estPair = n -> n % 2 == 0;
IntSupplier de = () -> (int)(Math.random() * 6) + 1;
```

Classe : `InterfacesPrimitives.java`

## Étape 5 : Différentes syntaxes de lambda

Explorer les syntaxes possibles : sans paramètre, avec un paramètre (typé ou non), plusieurs paramètres, corps en bloc.

```java
Runnable r1 = () -> System.out.println("Hello");
BiFunction<Integer, Integer, Integer> max = (a, b) -> {
    if (a > b) return a;
    else return b;
};
```

Classe : `SyntaxeLambda.java`

## Étape 6 : Capture de variables

Illustrer la capture d'une variable locale (effectivement finale) et de `this` dans une lambda.

```java
int facteur = 10;
IntUnaryOperator multiplicateur = n -> n * facteur;
```

Classe : `CaptureLambda.java`

## Étape 7 : Références de méthodes

Utiliser les quatre formes de références de méthodes : statique, instance sur objet particulier, instance sur type arbitraire, constructeur.

```java
Function<String, Integer> parser = Integer::parseInt;
Consumer<String> printer = System.out::println;
BiFunction<String, String, Boolean> comparateur = String::equalsIgnoreCase;
Supplier<List<String>> listFactory = ArrayList::new;
```

Classe : `ReferencesMethodes.java`

## Étape 8 : L'API Stream avec des lambdas

Utiliser `filter`, `map` et `count` pour traiter une liste de chaînes de façon déclarative.

```java
List<String> nomsP = noms.stream()
                         .filter(nom -> nom.startsWith("P"))
                         .collect(Collectors.toList());
```

Classe : `StreamBasics.java`

## Étape 9 : Opérations avancées avec Stream

Regrouper (`groupingBy`), calculer une moyenne (`mapToInt().average()`), trouver un maximum, et chaîner `filter`/`map` sur une liste d'objets `Personne`.

```java
Map<String, List<Personne>> parVille = personnes.stream()
                                              .collect(Collectors.groupingBy(Personne::getVille));
```

Classes : `Personne.java` (classe utilitaire), `StreamAvance.java`

## Étape 10 : Composer des fonctions

Combiner des `Function` avec `andThen`/`compose`, et des `Predicate` avec `and`/`or`/`negate`.

```java
Function<String, Boolean> longueurEstPaire = longueur.andThen(estPair);
Predicate<String> commenceParAEtLong = commenceParA.and(longueurSup5);
```

Classe : `CompositionFonctions.java`

## Étape 11 : Gérer les exceptions dans les lambdas

Gérer une exception vérifiée à l'intérieur d'une lambda via try-catch local, ou l'envelopper via une interface fonctionnelle personnalisée (`IOFunction`) qui la relance en `RuntimeException`.

```java
@FunctionalInterface
interface IOFunction<T, R> {
    R apply(T t) throws IOException;

    static <T, R> Function<T, R> unchecked(IOFunction<T, R> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }
}
```

Classe : `ExceptionsLambda.java`

## Étape 12 : Validation de formulaire

Créer un `Validateur<T>` générique qui enchaîne des règles (`Predicate<T>` + message d'erreur) de façon fluide.

```java
public Validateur<T> ajouterRegle(Predicate<T> test, String messageErreur) {
    regles.add(new Regle<>(test, messageErreur));
    return this;
}
```

Classes : `Validateur.java`, `ValidationFormulaire.java`

## Étape 13 : Implémentation de TriFunction

Définir une interface fonctionnelle à trois paramètres avec une méthode `andThen` pour chaîner un post-traitement.

```java
@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);

    default <V> TriFunction<A, B, C, V> andThen(Function<? super R, ? extends V> after) {
        return (a, b, c) -> after.apply(apply(a, b, c));
    }
}
```

Classe : `TriFunctionDemo.java`

## Étape 14 : Comptage de TODO dans des fichiers Java

Parcourir récursivement les fichiers `.java` du répertoire courant avec `Files.walk`, compter les lignes contenant « TODO » par fichier, et afficher les résultats triés par nombre décroissant.

```java
Map<Path, Long> todoParFichier = Files.walk(Paths.get("."))
    .filter(p -> p.toString().endsWith(".java"))
    .collect(Collectors.toMap(p -> p, p -> {
        try {
            return Files.lines(p).filter(line -> line.contains("TODO")).count();
        } catch (IOException e) {
            return 0L;
        }
    }));
```

Classe : `CompteurTODO.java`

## Étape 15 : Comparateur composé

Construire un tri multiclés pour des objets `Personne` (nom, puis prénom, puis âge) avec `Comparator.comparing` et `thenComparing`.

```java
Comparator<Personne> comparateur = Comparator.comparing(Personne::getNom)
    .thenComparing(Personne::getPrenom)
    .thenComparingInt(Personne::getAge);
```

Classe : à créer (le code source détaillé de cette étape n'était pas présent dans le document fourni — seule l'explication conceptuelle l'était)

## Étape 16 : Factorielle avec IntStream

Comparer une factorielle calculée de façon impérative (boucle) et de façon fonctionnelle avec `IntStream.rangeClosed` et `reduce`.

```java
private static long factorielleStream(int n) {
    if (n <= 1) return 1;
    return IntStream.rangeClosed(2, n)
                   .mapToLong(Long::valueOf)
                   .reduce(1, (a, b) -> a * b);
}
```

Classe : `FactorielleStream.java`

## Étape 17 : Memoizer générique

Implémenter un mécanisme de mise en cache générique (`memoize`) basé sur `ConcurrentHashMap`, appliqué à un calcul de Fibonacci récursif coûteux.

```java
public static <T, R> Function<T, R> memoize(Function<T, R> function) {
    Map<T, R> cache = new ConcurrentHashMap<>();
    return input -> cache.computeIfAbsent(input, function);
}
```

Classe : `Memoizer.java`

## Structure du projet

```
TP15_Lambdas/
├── src/
│   ├── LambdaIntro.java
│   ├── InterfacesFonctionnelles.java
│   ├── InterfacesPrimitives.java
│   ├── SyntaxeLambda.java
│   ├── CaptureLambda.java
│   ├── ReferencesMethodes.java
│   ├── StreamBasics.java
│   ├── Personne.java
│   ├── StreamAvance.java
│   ├── CompositionFonctions.java
│   ├── ExceptionsLambda.java
│   ├── Validateur.java
│   ├── ValidationFormulaire.java
│   ├── TriFunctionDemo.java
│   ├── CompteurTODO.java
│   ├── ComparateurCompose.java
│   ├── FactorielleStream.java
│   └── Memoizer.java
├── README.md
└── videos/
    └── demo.mp4
```

## Concepts mobilisés

- Expressions lambda et interfaces fonctionnelles (`@FunctionalInterface`)
- Interfaces standard (Predicate, Consumer, Function, Supplier) et primitives (IntPredicate, IntConsumer, IntFunction, IntSupplier)
- Capture de variables effectivement finales et de `this`
- Références de méthodes (statique, instance, constructeur)
- API Stream : filter, map, count, groupingBy, reduce, Files.walk/Files.lines
- Composition de fonctions (andThen, compose) et de prédicats (and, or, negate)
- Gestion fonctionnelle des exceptions vérifiées
- Comparateurs composés (thenComparing)
- Memoization générique avec ConcurrentHashMap

## Démo vidéo

Une seule vidéo montre l'exécution des 17 étapes, dans l'ordre.

[Voir la démo vidéo](videos/demo.mp4)

## Auteur

Soufiane Ait Hmad — TP15 Java, ENS Marrakech
