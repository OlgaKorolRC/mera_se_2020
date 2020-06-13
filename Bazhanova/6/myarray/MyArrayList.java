package myarray;


public class MyArrayList < T > {
    private final int INIT_SIZE = 4; // начальный размер списка
    private final int CUT_RATE = 4;
    private final int MAX_SIZE = 10;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;

    /*
    Добавляет новый элемент в MyArrayList. При достижении размера внутреннего
    массива происходит его увеличение на 1.
    */
    public void add(T item) throws MyArrayStoreException {
        if (pointer >= MAX_SIZE) throw new MyArrayStoreException("Попытка превышения лимита по количеству элементов, достигнуто максимально допустимое число элементов в списке");
        else {
            if ((pointer == array.length - 1))
                resize(array.length + 1); // увеличиваем на 1, если достигли границ и число элементов < MAX_SIZE
            array[pointer++] = item;
        }
        // if (pointer > MAX_SIZE) throw new MyArrayStoreException("Попытка превышения лимита по количеству элементов, достигнуто максимально допустимое число элементов в списке");
    }

    /*
    Возвращает элемент списка по индексу.
    */
    public T get(int index) {
        return (T) array[index];
    }

    /*
    Удаляет элемент списка по индексу. Все элементы справа от удаляемого 
    перемещаются на шаг налево. Если после удаления элемента количество    
    элементов стало в CUT_RATE раз меньше чем размер внутреннего массива, 
    то внутренний массив уменьшается в два раза, чтобы экономить место.
    */
    public void remove(int index) {
        for (int i = index; i < pointer; i++) {
            array[i] = array[i + 1];
        }
        
        array[pointer] = null;
        pointer--;
        if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
            resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем 
        // длина массива, то уменьшу в два раза
    }
    /*Возвращает количество элементов MyArrayList*/
    public int size() {
        return pointer;
    }

    /*Изменяет длину MyArrayList.*/
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
