#include <stdio.h>
tamsayı ana() {
    tamsayı t1 = 0, t2 = 1, nextTerm = 0, n;
    yazf("Enter a positive number: ");
    taraf("%d", &n);

    // displays the first two terms which is always 0 and 1
    yazf("Fibonacci Series: %d, %d, ", t1, t2);
    nextTerm = t1 + t2;

    iken (nextTerm <= n) {
        yazf("%d, ", nextTerm);
        t1 = t2;
        t2 = nextTerm;
        nextTerm = t1 + t2;
    }

    cevapVer 0;
}