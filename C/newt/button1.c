#include <newt.h>
#include <stdlib.h>
#include <stdio.h>

void main(void) {
    newtComponent form, label, entry, button;
    char * entryValue;

    newtInit();
    newtCls();

    newtOpenWindow(10, 5, 40, 8, "Entry and Label Sample");

    label = newtLabel(1, 1, "Enter a string");
    entry = newtEntry(16, 1, "sample", 20, &entryValue, 
                      NEWT_FLAG_SCROLL | NEWT_FLAG_RETURNEXIT);
    button = newtButton(17, 3, "Ok");
    form = newtForm(NULL, NULL, 0);
    newtFormAddComponents(form, label, entry, button, NULL);

    newtRunForm(form);

    newtFinished();

    printf("Final string was: %s\n", entryValue);

    /* We cannot destroy the form until after we've used the value
       from the entry widget. */
    newtFormDestroy(form);
}
