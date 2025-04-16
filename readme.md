# common

nayrid's common library code.

### Modules

- `common-api`: general-purpose useful code that I find myself repeating often
- `common-examine`: [examination](https://github.com/KyoriPowered/examination) stuff

## Gradle

First include our repository:

```kotlin
maven {
    url = uri("https://repo.nayrid.com/public")
}
```

And then use this library (change `(the latest version)` to the latest version):

```kotlin
implementation("com.nayrid.common:common-api:(the latest version)")

implementation("com.nayrid.common:common-examine:(the latest version)")
```
