https://courses.cs.washington.edu/courses/cse373/15au/homework1/

## The Program

You will write a program that reads a sound file in the .dat format (explained below) and writes another .dat sound file which is the reverse of the first. We have provided a class Reverse whose main method reads in a .dat sound file, pushes all the sound values on a stack, then pops them all off and writes them into a new .dat sound file. We have also provided an interface DStack, which defines a stack that holds double values. Your first job is to familiarize yourself with these files.

## Implementing the Stack ADT

You need to provide two stack implementations, one using an array and one using a linked list. They should be called ArrayStack and ListStack, respectively. They should implement the DStack interface given to you. Once you provide these implementations, Reverse should work and create backward sound files. It should take no more than a page or two of code to provide the implementations. Your array implementation should start with a small array (say, 10 elements) and resize to use an array twice as large whenever the array becomes full, copying over the elements in the smaller array. While there are convenient Java library methods for copying arrays, for this assignment, use your own loop to copy array elements manually (so you can "see" the work involved in copying).

Both ArrayStack and ListStack should throw an EmptyStackException if pop() or peek() is called when the stack is empty. To use EmptyStackException, add the following line to your file:

import java.util.EmptyStackException;

The only Java class that you should use to complete the implementations of your stacks is java.util.EmptyStackException. You should also use the length field of an array.

## Running Reverse

The Reverse program takes 3 arguments (also known as "command-line arguments"). The first is the word array or list and specifies which implementation to use. The next two are the input and output .dat file names (you need to include the .dat extension). Running the program will depend on your system; from a command line it will look something like:

java Reverse list in.dat out.dat
In an IDE there is usually a dialog box for setting program parameters which contains a field for the program arguments. (For example, in jGRASP select Build->Run Arguments and a bar will appear at the top of the screen that allows you to type in the arguments. See Working with Eclipse below for information on setting command line parameters in Eclipse.)

To test your program, you will need a .dat file, which you can create from a .wav file as explained in the Digital Sound section. It is also useful to create short .dat files by hand to aid testing.

## Testing Your Stack Implementations

We have provided you a client program, Reverse.java, that uses the stack implementations you will write. Getting Reverse.java to run and correctly reverse a sound file is fun and indicates that your stack implementations will compile and run. It does not, however, imply that your stack implementations have been thoroughly tested. Reverse.java just uses your stacks in one particular way: pushing a bunch of elements onto the stack and then popping them all off. There are other ways that someone use your stack implementations and other cases that are not tested by just being able to successfully listen to sound files in reverse.

We will be testing your stack implementations more generally and you should too! We highly suggest creating another small client program of your own to help test other aspects of your code. You also might consider creating some short (e.g., 10 line) .dat files by hand to aid testing. (See the ".dat File Format" section of the write-up below for more information).

## Write-Up Questions

Turn in a file README.txt containing answers to the following questions.

How did you test that your stack implementations were correct?
The file secret.wav is a backwards recording of a word or short phrase. Use sox (or another converter) and your program to reverse it, and write that as the answer to this question.
Your array stacks start with a small array and double in size if they become full. For a .dat file with 1 million lines, how many times would this resizing occur? What about with 1 billion lines or 1 trillion lines (assuming the computer had enough memory)? Explain your answer.
Suppose that, instead of a DStack interface, you were given a fully-functional FIFO Queue class. How might you implement this project (i.e., simulate a Stack) with one or more instances of a FIFO Queue? 
Write pseudocode for your push and pop operations. Refer to the Written-Homework Guidelines for instructions on writing pseudocode. Assume your Queue class provides the operations enqueue, dequeue, isEmpty, and size.
In the previous question, what trade-offs did you notice between a Queue implementation of a Stack and your original array-based implementation? Which implementation would you choose, and why?
Include a description of how your project goes "above and beyond" the basic requirements (if it does).
What did you enjoy about this assignment? What did you not enjoy? What could you have done better?
What else, if anything, would you would like to include related to this homework?
Going Above and Beyond

The following list of suggestions are meant for you to try if you finish the requirements early. Recall that extra-credit points earned for "above and beyond" are kept separate and will be used to adjust your grade at the end of the quarter, as detailed in the course grading policy.

(1 point) Modify your array implementations so that when the array is 3/4 empty, the stack resizes to use an array of half the size.
(3 points) Implement the Karplus-Strong algorithm (to generate "reverb" sounds). A description of this algorithm is available here.
