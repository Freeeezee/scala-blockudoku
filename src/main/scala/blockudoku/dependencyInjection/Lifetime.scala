package blockudoku.dependencyInjection

/**
 * Component instantiation policy. <br><br>
 * Singleton: A single instance of the component is created and shared among all consumers. <br>
 * Transient: A new instance of the component is created for each consumer.
 * @see [[ComponentContainer]], [[ComponentProvider]]
 */
enum Lifetime {
  case Singleton
  case Transient
}