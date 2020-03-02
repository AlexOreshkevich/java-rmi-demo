package compute;

/**
 * Defines the interface between the compute engine and the work that it needs to do, providing the way to start the work.
 *
 * @param <T> represents the result type of the task's computation
 */
public interface Task<T> {

  T execute();
}
