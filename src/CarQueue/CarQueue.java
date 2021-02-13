package CarQueue;

import Entities.Car;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

public class CarQueue implements Queue<Car> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Car> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Car> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(Car car) {
        return false;
    }

    @Override
    public Car remove() {
        return null;
    }

    @Override
    public Car poll() {
        return null;
    }

    @Override
    public Car element() {
        return null;
    }

    @Override
    public Car peek() {
        return null;
    }
}
