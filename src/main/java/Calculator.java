public class Calculator {
    public static void main(String[] args) {

        /**
         * implementing LinkedStack method
         * initializing variables according to instructions
         */
        String exp = "a/b(c+(d-e))";
        //String exp1 = "mn+(p-q)+r";
        LinkedStack<Character> stck1 = new LinkedStack<Character>();
        System.out.println(stck1.convertToPostfix(exp));


        /**
         * implementing resizeableArrayStack method
         * initializing variables according to “A Problem Solved: Evaluating Postfix Expressions”
         * according to the textbook
         */
        //String exp1 = ("2 * 3 / ( 4 - 2 ) + 5 * 6");
        String exp1 = ("2 3 * 4 2 - / 5 6 * +");
        ResizeableArrayStack<Integer> arrayStackTest = new ResizeableArrayStack<>();
        ResizeableArrayStack<Integer> hello = arrayStackTest.evaluatePostfix(exp1);
        System.out.println(hello);
    }
}
