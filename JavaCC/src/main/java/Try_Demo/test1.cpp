/*
  test code version 1.0
*/
void main() {
    // [test] declaration list.
    int a = 12, b, i = 1;
    // [test] if else ambiguty.
    if (a > 10)
        b = 1;
    if (0 < a && a <= 10)
        b = 0;
    else
        b = -1;
    // [test] for statement compound statement and expression statement.
    for(;i<3;i=i+1){;}
    // [test] priority of operations.
    a = 1+3*(52-1);
    // [test] io statement.
    print(b);
}
