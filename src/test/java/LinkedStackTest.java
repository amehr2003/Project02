import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedStackTest
{
    @org.junit.jupiter.api.Test
    void testPush() {
        //Arrange
        LinkedStack<String> linkedStackTest = new LinkedStack<>();

        //Act
        linkedStackTest.push(String.valueOf('1'));
        Object test = linkedStackTest.peek();

        //Assert
        assertEquals(String.valueOf('1'), test);
    }

    @Test
    void testPop() {
        //Arrange
        LinkedStack<String> linkedStackTest = new LinkedStack<>();

        //Act
        linkedStackTest.push(String.valueOf('1'));
        Object test = linkedStackTest.peek();

        //Assert
        assertEquals(String.valueOf('1'), test);
    }

    @Test
    void testPeek() {
        //Arrange
        LinkedStack<String> linkedStackTest = new LinkedStack<>();

        //Act
        linkedStackTest.push(String.valueOf('1'));
        Object test = linkedStackTest.peek();

        //Assert
        assertEquals(String.valueOf('1'), test);
    }
    @Test
    void testConvertPostfix()
    {
        //String exp = "a/b(c+(d-e))";
        String exp1 = "mn+(p-q)+r";
        LinkedStack<Character> stck1 = new LinkedStack<Character>();
        String actual= "a/(b−c)*d";
        assertEquals(stck1.convertToPostfix(exp1), actual);
    }
}