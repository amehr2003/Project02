//package edu.cpp.ds.stacks;

import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T> {
	private Node<T> topNode; // References the first node in the chain
	private static final int MAX_CAPACITY = 1000;


	public void push(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);

		if (topNode == null) {
			topNode = newNode;
		} else {
			Node<T> temp = topNode;
			topNode = newNode;
			newNode.next = temp;
		}
		//System.out.println(newEntry + " pushed to stack");
	}

	public T pop() {
		T retValue = null;
		if (topNode == null) {
			throw new EmptyStackException();
		} else {
			retValue = topNode.data;
			topNode = topNode.next;
		}
		return retValue;
	}

	public T peek() {
		if (topNode == null) {
			//System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			return topNode.data;
		}
	}

	public boolean isEmpty() {
		if (topNode == null) {
			return true;
		} else
			return false;
	}

	public void clear() {
		if (topNode == null) {
			//System.out.println("Stack is empty");
			throw new EmptyStackException();
		} else {
			while (topNode.next != null) {
				topNode.data = null;
			}
		}
	}
	
	private static boolean isPaired(char open, char close) {
		return (open == '(' && close == ')') || (open == '[' && close == ']') || (open == '{' && close == '}');
	} // end isPaired
	
	static boolean checkBalance(String exp) {

		StackInterface<Character> openDelimiterStack = new LinkedStack<Character>();

		int characterCount = exp.length();
		boolean isBalanced = true;
		int index = 0;
		char nextCharacter = ' ';

		while (isBalanced && (index < characterCount)) {
			nextCharacter = exp.charAt(index);
			switch (nextCharacter) {
			case '(':
			case '[':
			case '{':
				openDelimiterStack.push(nextCharacter);
				break;
			case ')':
			case ']':
			case '}':
				if (openDelimiterStack.isEmpty())
					isBalanced = false;
				else {
					char openDelimiter = openDelimiterStack.pop();
					isBalanced = isPaired(openDelimiter, nextCharacter);
				} // end if
				break;
			default:
				break; // Ignore unexpected characters
			} // end switch
			index++;
		} // end while

		if (!openDelimiterStack.isEmpty())
			isBalanced = false;
		return isBalanced;
	} // end checkBalance

	
	/* This function returns associated precedence to an operator */

	static int Prec(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;

		case '*':
		case '/':
			return 2;

		case '^':
			return 3;
		}
		return -1;
	}



	
	public String convertToPostfix(String exp) {
		//Check the integrity of the input exp
		
		checkIntegrity(exp);
		
		String result = new String("");
		LinkedStack<Character> myStack = new LinkedStack<Character>();
		for (int i = 0; i < exp.length(); ++i) {
			char c = exp.charAt(i);

			// If the scanned character is an
			// operand, add it to output.
			if (Character.isLetterOrDigit(c))
				result += c;

			// If the scanned character is an '(',
			// push it to the stack.
			else if (c == '(')
				myStack.push(c);

			// If the scanned character is an ')',
			// pop and output from the stack
			// until an '(' is encountered.
			else if (c == ')') {
				while (!myStack.isEmpty() && myStack.peek() != '(')
					result += myStack.pop();

				myStack.pop();
			} else // an operator is encountered
			{
				while (!myStack.isEmpty() && Prec(c) <= Prec(myStack.peek())) {

					result += myStack.pop();
				}
				myStack.push(c);
			}

		}

		// pop all the operators from the stack
		while (!myStack.isEmpty()) {
			if (myStack.peek() == '(')
				return "Invalid Expression";
			result += myStack.pop();
		}
		return result;
	}
   
    private void checkIntegrity(String exp)
    {
    	if(!checkBalance(exp))
			throw new IllegalStateException("Expession is not balanced");
		if(exp.length() > MAX_CAPACITY)
			throw new IllegalStateException("Exceeded max Length");
		if(exp.equals(null))
			throw new IllegalStateException("Exp Cannot be null");
		if(exp.trim().length() == 0)
			throw new IllegalStateException("Exp Cannot be empty or blank");
		
        
    }  //end checkInitialization
   
    public boolean isOperator(char ch) //this method will be used in evaluatePostfix to check and make sure what it being used in the stack is correct
    {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            return true;
        }
        return false;
    }

	private class Node<T> {
		private T data; // Entry in stack
		private Node<T> next; // Link to next node

		private Node(T dataPortion) {
			this(dataPortion, null);
		} // end constructor

		private Node(T dataPortion, Node<T> linkPortion) {
			data = dataPortion;
			next = linkPortion;
		} // end constructor

		private T getData() {
			return data;
		} // end getData

		private void setData(T newData) {
			data = newData;
		} // end setData

		private Node<T> getNextNode() {
			return next;
		} // end getNextNode

		private void setNextNode(Node<T> nextNode) {
			next = nextNode;
		} // end setNextNode
	} // end Node

	public static void main(String args[]) {
		String exp = "a/b*(c+(d-e))";
		//String exp1 = "m*n+(p-q)+r";
		LinkedStack<Character> stck1 = new LinkedStack<Character>();
		System.out.println(stck1.convertToPostfix(exp));
	}
}
