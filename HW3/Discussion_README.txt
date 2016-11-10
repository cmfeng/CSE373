cmf927@uw.edu Changming Feng
hsiaop@uw.edu Preston Hsiao

1) Design Decision 1: We chose 7 to be our starting capacity because it is a prime number, and as we know, table size being prime helps to avoid collision. We considered some prime number like 5,7,11,1009, and 7 is  not too big, and not too small, to account for both small and large cases.

Design Decision 2: We expanded our internal capacity when the load factor reached 1 to keep llamda low for chaining. In this way, insert new word and new association would take little time.

Design Decision 3: The new size of the array is the next prime number greater than the double of the previous size because we need to keep our size prime as well as increase the size enough, but not by too much.

2) We used the string's hashcode method and get the remainder of the string's hashcode over TextAssociator array length.We used this hash function because the hashcode of String
was already built into the String class, and getting the remainder over the array length helps to get different index for two hashcodes in a certain length array.

3) If we were to use a different collision resolution scheme, we would use open addressing because we keep our load factor less than or equal to 1, so it will not do poorly, as it does with large load factors. Our code would change significantly, as there would be no separate chain. We would implement this class by directly inserting the WordInfo objects into the table according to a probe function. We think this would make our code simpler, without a separate chain, but the run time might be slower, with more resizes and finding indexes.

For example: 
When created, the table in the TexAssociator shoule be an array of WordInfo object.

In the addNewWord method, after calculating the index of the word by toHash function, there should be a loop that while the table element at the calculated index is not empty, keep on probing until finding an empty space in the table and add the WordInfo with the given word. If the index reaches the end of the table before finding a empty element, the table array should be resized.



4) We spent 4 days on this assignment. We found resizing to  be the most challenging part because it was confusing to recalculate the new indexes and re-add words as well as each association of the word into the table.

5)Favorite association of "hello world it is fun to write code and have fun with data structures":

"hail Old World better self is razz in passage to push the pen new morality and possess visual joke despite computer program structures"

6)Our myClient class is a client of TextAssociator that does a spell check on the words, "hello", "how", "are", "you", "doing", and "today".
 It prompts the user for an input phrase, sentence, or word that can be misspelled, and our client will print out the correct spelling of the words listed. We add some possible misspellings of these certain words to the TextAssociator and then add the correct word as association to each misspelling mentioned above. Then gets the misspelling word as input and prints the corresponding correct word which is associated to the misspelling word form TextAssociator.