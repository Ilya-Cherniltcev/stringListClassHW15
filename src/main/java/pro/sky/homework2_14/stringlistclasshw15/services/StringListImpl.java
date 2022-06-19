package pro.sky.homework2_14.stringlistclasshw15.services;

import org.springframework.stereotype.Service;
import pro.sky.homework2_14.stringlistclasshw15.interfaces.StringList;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.AbsentElementException;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.EmptyArrayException;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.IndexOutOfArrayException;

import java.util.Arrays;

@Service
public class StringListImpl implements StringList {
    private String[] strList;
    private int length = 0;

    public StringListImpl(int length) {
        this.strList = new String[length];
    }

    public StringListImpl() {
        this.strList = new String[]{};
    }

    @Override
    // *****  показать все элементы массива *****
    public String[] getAll() {
        return strList;
    }


    @Override
    // Добавление элемента.  Вернуть добавленный элемент в качестве результата выполнения.
    public String add(String item) {
        if (strList.length == 0) {
            strList = new String[1];
        } else {
            strList = Arrays.copyOf(strList, strList.length + 1);
        }
        strList[length] = item;
        length++;
        return item;
    }

    // Добавление элемента на определенную позицию списка.
    // Если выходит за пределы фактического количества элементов или массива,
    // выбросить исключение.  Вернуть добавленный элемент в качестве результата выполнения.
    @Override
    public String add(int index, String item) {
        validateIndex(index);
        if (strList.length == 0) {
            strList = new String[1];
            strList[index] = item;
            length++;
            return item;
        }
        length++;
        if (index != 0) {
            String[] firstPart = new String[index];
            System.arraycopy(strList, 0, firstPart, 0, index);
            int lastLength = strList.length - index;
            String[] lastPart = new String[lastLength];
            System.arraycopy(strList, index, lastPart, 0, lastLength);
            strList = new String[length];
            System.arraycopy(firstPart, 0, strList, 0, firstPart.length);
            strList[index] = item;
            System.arraycopy(lastPart, 0, strList, index + 1, lastLength);
        } else {
            String[] lastPart = new String[length - 1];
            System.arraycopy(strList, 0, lastPart, 0, lastPart.length);
            strList = new String[length];
            strList[0] = item;
            System.arraycopy(lastPart, 0, strList, 1, lastPart.length);
        }
        return item;
    }

    // Установить элемент на определенную позицию, затерев существующий.
    // Выбросить исключение, если индекс больше
    // фактического количества элементов или выходит за пределы массива.
    @Override
    public String set(int index, String item) {
        validateEmptyArray();
        validateIndex(index);
        strList[index] = item;
        return item;
    }

    // Удаление элемента.  Вернуть удаленный элемент
    // или исключение, если подобный  элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        validateEmptyArray();
        for (int i = 0; i < strList.length; i++) {
            if (strList[i].equals(item)) {
                String tempStr = remove(i);
                return item;
            }
        }
        throw new AbsentElementException("Element is absent");
    }

    // Удаление элемента по индексу.  Вернуть удаленный элемент
    // или исключение, если подобный  элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        validateEmptyArray();
        validateIndex(index);
        String item = strList[index];
        length--;
        // ***************** если удаляемый элемент на 0 позиции ********
        if (index != 0) {
            String[] firstPart = new String[index];
            System.arraycopy(strList, 0, firstPart, 0, index);
            String[] lastPart = new String[length - index];
            System.arraycopy(strList, index + 1, lastPart, 0, length - index);
            strList = new String[length];
            System.arraycopy(firstPart, 0, strList, 0, firstPart.length);
            System.arraycopy(lastPart, 0, strList, firstPart.length, lastPart.length);
        } else {
            // ***************** если удаляемый элемент на любой другой позиции ********
            String[] lastPart = new String[length];
            System.arraycopy(strList, 1, lastPart, 0, lastPart.length);
            strList = new String[length];
            System.arraycopy(lastPart, 0, strList, 0, lastPart.length);
        }
        return item;
    }

    // Проверка на существование элемента.  Вернуть true/false;
    @Override
    public boolean contains(String item) {
        validateEmptyArray();
        boolean isExist = false;
        for (String existStr : strList) {
            if (existStr.equals(item)) {
                isExist = true;
                break;
            }
        }
//        boolean isExist;
//        isExist = Arrays.stream(strList)
//                .findFirst()
//                .toString()
//                .equals(item);
        return isExist;
    }


    // Поиск элемента.  Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        validateEmptyArray();
        int isExistIndex = -1;
        for (int i = 0; i < strList.length; i++) {
            if (strList[i].equals(item)) {
                isExistIndex = i;
                break;
            }
        }
        return isExistIndex;
    }


    // Найти последний встречающийся элемент.  Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        validateEmptyArray();
        int isExistIndex = -1;
        for (int i = strList.length - 1; i >= 0; i--) {
            if (strList[i].equals(item)) {
                isExistIndex = i;
                break;
            }
        }
        return isExistIndex;
    }

    // Получить элемент по индексу.  Вернуть элемент или исключение,
    // если выходит за рамки фактического  количества элементов.
    @Override
    public String get(int index) {
        validateEmptyArray();
        validateIndex(index);
        String item = strList[index];
        return item;
    }


    // Сравнить текущий список с другим.  Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) {
        validateEmptyOtherList(otherList);
        String[] otherStringArray = otherList.getAll();
        boolean isEquals = false;
        if (otherStringArray.length == strList.length) {
            for (int i = 0; i < strList.length; i++) {
                if (!otherStringArray[i].equals(strList[i])) {
                    isEquals = false;
                    break;
                }
                isEquals = true;
            }
        }
        return isEquals;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return length;
    }

    // Вернуть true,  если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        strList = new String[]{};
        length = 0;
    }

    // Создать новый массив  из строк в списке и вернуть его.
    @Override
    public String[] toArray() {
        String[] newArray = new String[length];
        System.arraycopy(strList, 0, newArray, 0, length);
        return newArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > strList.length) {
            throw new IndexOutOfArrayException("Out of range");
        }
    }

    private void validateEmptyArray() {
        if (strList.length == 0) {
            throw new EmptyArrayException("The list is empty");
        }
    }

    private void validateEmptyOtherList(StringList otherList) {
        if (otherList == null) {
            throw new EmptyArrayException("The list is empty");
        }
    }

    public String toString(String[] tempList) {
        return Arrays.toString(tempList);
    }
}
