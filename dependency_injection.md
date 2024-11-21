# Dependency Injection

## Target

1. **Automatic**: Dependencies should be resolved implicitly without calling a `resolve()` function.
2. **Compile-Time Safety**: The compiler should be able to check whether all required dependencies are registered or not.
3. **Ease of configuration**: Components should be configured in one central method at startup and ideally it should also be possible to delegate configurations to sub-methods.

## Options & Frameworks

| **Name**             | **Automatic** | **Compile-Time Safe** | **Ease of configuration**                                    |
| -------------------- | ------------- | --------------------- | ------------------------------------------------------------ |
| Guice                | ❌             | ❌                     | ✅                                                            |
| Spring               | ✅             | ❌                     | ✅ though pretty verbose                                      |
| Macwire              | ✅             | ✅                     | partially, requires same scope and explicit calls to wire functions |
| Native Given         | ✅             | ✅                     | partially, requires same scope and setup is not clearly related to DI. |
| Reflection Container | ✅             | ❌                     | ✅                                                            |

## Reflection Container

- A custom implementation of DI in a similar way to the .NET framework. 
- Components are registered at a container data structure and injected through reflection. 
- Not compile-time safe but can be configured to check dependencies at startup.
