#include <iostream>
#include <string>
using namespace std;

int longestStringLength(string arr[], int n) {
    int maxLength = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i].length() > maxLength) {
            maxLength = arr[i].length();
        }
    }
    return maxLength;
}

int main() {
    string words[] = {"apple", "banana", "cherry", "date"};
    int n = sizeof(words) / sizeof(words[0]);

    int longestLength = longestStringLength(words, n);
    cout << "Longest string length is: " << longestLength << endl;

    return 0;
}
