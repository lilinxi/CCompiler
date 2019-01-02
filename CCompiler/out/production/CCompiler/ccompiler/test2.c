int main()
{
    int i = 5;
    int j;
    do
    {
        if (i < 1 + 1)
            j=i;
        i = i - 1;
    } while (i > 0);
    while (i < 8)
    {
        if (i > 10 / 2)
            j=i;
        else
        {
            j=1*10;
        }
        i = i + 1;
    }
    return 0;
}