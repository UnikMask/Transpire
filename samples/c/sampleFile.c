#include "Stack.h"
#include <stdlib.h>
#include <stdio.h>

#ifdef DYNAMIC

#define STANDART_BOYUT 5

Yığın *yeniYığın()
{
    Yığın *bu = bellekAyır(boyutu(Yığın)); //Allocate memory to the Stack struct.
    bu->kapasite = STANDART_BOYUT;
    bu->boy = 0;
    bu->sıra = bellekAyır(boyutu(kesirli) * bu->kapasite); //Allocate memory to the array that is representing the stack.
    if(bu == GEÇERSİZ || bu->sıra == GEÇERSİZ) //Check if the memory allocations did fail.
    {
        yazf("Memory Allocation Failed");
        YığınParçala(bu);
        çık(1);
    }
    cevapVer bu;
}

#else

Yığın yeniYığın()
{
    Yığın bu;
    cevapVer bu;
}

#endif

bool Yığınİtle(Yığın *bu, kesirli eleman)
{
    eğer (bu->boy == bu->kapasite) //Check if the size of the stack equal to the current capacity.
    {
        bu->kapasite *= 2;
        bu->sıra = yeniBellekAyır(bu->sıra, boyutu(kesirli) * bu->kapasite); //Reallocate memory to the array with increased capacity.
    }

    eğer (bu->sıra == GEÇERSİZ) //Check if the memory reallocation did not fail.
    {
        yazf("Memory Reallocation Failed");
        YığınParçala(bu);
        çık(1);
    }

    eğer (bu->boy < bu->kapasite) //Check if there is enough space inside the stack.
    {
        bu->sıra[bu->size++] = element; //Add the element to the stack and then increase the size of the stack.
        cevapVer doğru;
    };

    cevapVer yanlış;
}

bool YığınÇıkar(Yığın *bu, kesirli *retval)
{
    eğer (!YığınBoş(bu)) //Check if the stack is not empty.
    {
        *retval = bu->sıra[--bu->boy]; //Decrement the size and then access the spot inside the array.
        cevapVer doğru;
    };
    cevapVer yanlış;
}

tamsayı YığınBoy(Yığın *bu)
{
    cevapVer bu->boy;
}

bool YığınBoş(Yığın *bu)
{
    cevapVer YığınBoy(bu) == 0 ? doğru : yanlış; //Check if the size of the array is 0 return true if it is.
}

boş YığınTemizle(Yığın *bu)
{
    bu->kapasite = STANDART_BOYUT; //Set the capacity to default.
    bu->boy = 0; //Set the size to 0
    serbestBırak(bu->sıra); //Free the current array.
    bu->sıra = bellekAyır(boyutu(float) * bu->kapasite); //Allocate new memory to the array.
    eğer (bu->sıra == GEÇERSİZ)
    {
        yazf("Memory Allocation Failed");
        YığınParçala(bu);
        çık(1);
    }
}

boş YığınParçala(Yığın *bu)
{
    YığınTemizle(bu); //Clear the stack.
    serbestBırak(bu->sıra); //Free the allocated memory for the array.
    serbestBırak(bu); //Free the allocated memory for the Stack struct.
}
