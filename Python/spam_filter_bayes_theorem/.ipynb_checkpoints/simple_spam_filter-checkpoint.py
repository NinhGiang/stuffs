import pandas as pd
import re, math
# step 0: import dataset, calculate probability of ham/spam in the dataset
# step 1: receive mail, extract words into a list
# step 2: calculate probability of occuring each word in the ham/spam set
# step 3: calculate probability of spam knowing these words.

def get_tf(word_list):
    result = list()
    for word in word_list:
        if (1 + math.log(word_list.count(word)))/math.log(len(word_list)) < 0.3:
            result.append(word)
        result = list(dict.fromkeys(result))
    return result

def extract_words(text):
    split_list = re.split('; |;|, |,|: |:|\t|\*|\n|! | |\.|\. ', str(text))
    word_list = [word.lower() for word in split_list]
    tf_list = get_tf(word_list)
    word_list = list(dict.fromkeys(word_list))
    for word in word_list:
        if word in tf_list:
            word_list.remove(word)
    if str('') in word_list:
        word_list.remove('')
    return word_list

def prob_in_set(word, list):
    count = 0
    for mail in list:
        if word in str(mail).lower():
            count += 1
    return count/len(list)

def product_of_num_list(num_list):
    result = 1
    for num in num_list:
        result *= float(num + 10)
    return result

fraud_email = pd.read_csv('fraud_email.csv')
spam_list = fraud_email[(fraud_email['Class'] == 1)]['Text'].tolist()
ham_list = fraud_email[(fraud_email['Class'] == 0)]['Text'].tolist()
dataset_row_count = len(fraud_email.axes[0])
prob_ham_in_dataset = len(ham_list)/dataset_row_count
prob_spam_in_dataset = len(spam_list)/dataset_row_count

def main():
    text = input('Please input a text: ')
    word_list = extract_words(text)
    prob_in_spam_list = list()
    prob_in_ham_list = list()
    for word in word_list:
        prob_in_spam_list.append(prob_in_set(word, spam_list))
        prob_in_ham_list.append(prob_in_set(word, ham_list))
    ratio = (product_of_num_list(prob_in_ham_list) * prob_ham_in_dataset) / \
        (product_of_num_list(prob_in_spam_list) * prob_spam_in_dataset)
    print('Ratio: ' + str(ratio))
    prob = 1 / (1 + ratio)
    print("You email has a chance of " + str(prob) + "% of being a spam.")

def test():
    result_list = list()
    print('Hi')
    for text in spam_list:
        word_list = extract_words(text)
        prob_in_spam_list = list()
        prob_in_ham_list = list()
        for word in word_list:
            prob_in_spam_list.append(prob_in_set(word, spam_list))
            prob_in_ham_list.append(prob_in_set(word, ham_list))
        ratio = (product_of_num_list(prob_in_ham_list) * prob_ham_in_dataset) / \
            (product_of_num_list(prob_in_spam_list) * prob_spam_in_dataset)
        prob = 1 / (1 + ratio)
        result_list.append(prob)
    for i in range(len(result_list)):
        print(result_list[i], end = ' ')
        if i%20 == 0:
            print()
test()
#main()