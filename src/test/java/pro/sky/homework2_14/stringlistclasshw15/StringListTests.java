package pro.sky.homework2_14.stringlistclasshw15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.AbsentElementException;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.EmptyArrayException;
import pro.sky.homework2_14.stringlistclasshw15.exceptions.IndexOutOfArrayException;
import pro.sky.homework2_14.stringlistclasshw15.interfaces.StringList;
import pro.sky.homework2_14.stringlistclasshw15.services.StringListImpl;

import static pro.sky.homework2_14.stringlistclasshw15.StringListTestConstants.*;

@SpringBootTest
class StringListTests {
    private StringListImpl stringList;

    @BeforeEach
    void setup() {
        stringList = new StringListImpl();
        stringList.add("0");
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("0");
        stringList.add("4");
    }

    @Test
    void shouldReturnAllElementsOfArray() {
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(ARRAY_LIST, actual);
    }

    @Test
    void shouldReturnRightArrayAfterAddingElement() {
        stringList.add("AAA");
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(LIST_AFTER_ADD, actual);
    }

    @Test
    void shouldReturnExceptionIfOutOfRange() {
        Assertions.assertThrows(IndexOutOfArrayException.class,
                () -> stringList.add(10, "example"));
    }

    @Test
    void shouldReturnRightArrayAfterAddingElementByIndex() {
        stringList.add(2, "BB");
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(LIST_AFTER_ADD_BY_INDEX, actual);
    }

    @Test
    void shouldReturnRightArrayAfterSettingOfElement() {
        stringList.set(2, "oops");
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(LIST_AFTER_SET, actual);
    }

    @Test
    void shouldReturnExceptionIfArrayIsEmptyByRemoving() {
        stringList.clear();
        Assertions.assertThrows(EmptyArrayException.class,
                () -> stringList.remove("example"));
    }

    @Test
    void shouldReturnRightArrayAfterRemovingOfElement() {
        stringList.remove("1");
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(LIST_AFTER_REMOVE, actual);
    }

    @Test
    void shouldReturnExceptionIfElementIsAbsentByRemoving() {
        Assertions.assertThrows(AbsentElementException.class,
                () -> stringList.remove("example"));
    }


    @Test
    void shouldReturnExceptionIfArrayIsOutOfRangeByRemovingByIndex() {
        Assertions.assertThrows(IndexOutOfArrayException.class,
                () -> stringList.remove(10));
    }

    @Test
    void shouldReturnRightArrayAfterRemovingOfElementByIndex() {
        stringList.remove(1);
        String[] actual = stringList.getAll();
        Assertions.assertArrayEquals(LIST_AFTER_REMOVE, actual);
    }

    @Test
    void shouldReturnTrueIfElementIsExist() {
        boolean actual = stringList.contains("1");
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnFalseIfElementIsNotExist() {
        boolean actual = stringList.contains("example");
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnMinusOneIfElementIsNotExistByIndexOf() {
        int actual = stringList.indexOf("example");
        Assertions.assertEquals(-1, actual);
    }

    @Test
    void shouldReturnRightIndexOfElement() {
        int actual = stringList.indexOf("2");
        Assertions.assertEquals(2, actual);
    }

    @Test
    void shouldReturnRightIndexOfLastElement() {
        int actual = stringList.lastIndexOf("0");
        Assertions.assertEquals(4, actual);
    }

    @Test
    void shouldReturnExceptionIfArrayIsOutOfRangeByGettingByIndex() {
        Assertions.assertThrows(IndexOutOfArrayException.class,
                () -> stringList.get(10));
    }

    @Test
    void shouldReturnRightElementByGettingByIndex() {
        String actual = stringList.get(2);
        Assertions.assertEquals(ELEMENT, actual);
    }

    @Test
    void shouldReturnTrueIfListsAreEquals() {
        StringListImpl otherList = new StringListImpl();
        otherList.add("0");
        otherList.add("1");
        otherList.add("2");
        otherList.add("3");
        otherList.add("0");
        otherList.add("4");
        System.out.println(otherList);
        boolean actual = stringList.equals(otherList);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnExceptionIfArrayIsNullByEquals() {
        Assertions.assertThrows(EmptyArrayException.class,
                () -> stringList.equals(null));
    }

    @Test
    void shouldReturnSizeOfArray() {
        int actual = stringList.size();
        Assertions.assertEquals(ARRAY_LIST_LENGTH, actual);
    }

    @Test
    void shouldReturnTrueIfArrayIsEmpty() {
        stringList.clear();
        boolean actual = stringList.isEmpty();
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnFalseIfArrayIsNotEmpty() {
        boolean actual = stringList.isEmpty();
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnEmptyArrayAfterClearing() {
        stringList.clear();
        int sizeOfList = stringList.size();
        Assertions.assertEquals(0, sizeOfList);
    }

    @Test
    void shouldReturnNewArray() {
        String[] actualStringList = stringList.toArray();
        String actual = stringList.toString(actualStringList);
        String expected = stringList.toString(ARRAY_LIST);
        Assertions.assertEquals(expected, actual);
    }


}
