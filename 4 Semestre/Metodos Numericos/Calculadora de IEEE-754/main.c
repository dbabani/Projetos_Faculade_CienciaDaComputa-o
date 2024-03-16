#include <stdio.h>
#include <float.h>
#include <math.h>
#include <fenv.h>


void printIEE754(unsigned long long int num) {
    for (int i = 31; i >= 0; i--) {
        printf("%llu", (num >> i) & 1);
        if (i % 4 == 0) {
            printf(" ");
        }
    }
    printf("\n");
}


void excecoes(){
    if(fetestexcept(FE_INEXACT)){
        printf("Exceção FE_INEXACT:1\n");
    }else{
        printf("Exceção FE_INEXACT:0\n");
    }
    if(fetestexcept(FE_DIVBYZERO)){
        printf("Exceção FE_DIVBYZERO:1\n");
    }else{
        printf("Exceção FE_DIVBYZERO:0\n");
    }
    if(fetestexcept(FE_UNDERFLOW)){
        printf("Exceção FE_UNDERFLOW:1\n");
    }else{
        printf("Exceção FE_UNDERFLOW:0\n");
    }
    if(fetestexcept(FE_OVERFLOW)){
        printf("Exceção FE_OVERFLOW:1\n");
    }else{
        printf("Exceção FE_OVERFLOW:0\n");
    }
    if(fetestexcept(FE_INVALID)){
        printf("Exceção FE_INVALID:1\n");
    }else{
        printf("Exceção FE_INVALID:0\n");
    }
}



float add(float a, float b) {
    return a + b;
}

float subtract(float a, float b) {
    return a - b;
}

float multiply(float a, float b) {
    return a*b;
}

float divide(float a, float b) {
    return a/b;
}

int main() {
    float value1, value2;
    char operation;
    

    printf("Digite o valor 1, operação e valor 2: ");
    scanf("%f %c %f", &value1, &operation, &value2);

    float result;

    switch (operation) {
        case '+':
            result = add(value1, value2);
            break;
        case '-':
            result = subtract(value1, value2);
            break;
        case '*':
            result = multiply(value1, value2);
            break;
        case '/':
            result = divide(value1, value2);
            break;
        default:
            printf("Operação inválida.\n");
            return 1;
    }



    unsigned long long int ieee_754_representation_VALUE1 = *(unsigned long long int*)&value1;
    unsigned long long int ieee_754_representation_VALUE2 = *(unsigned long long int*)&value2;
    unsigned long long int ieee_754_representation_RESULT = *(unsigned long long int*)&result;


    printf("Recebi %f %c %f e resultado deu %f", value1, operation, value2, result);
    
    printf("\n\n val1 = ");
    printIEE754(ieee_754_representation_VALUE1);
    printf("= %f",value1);
    printf("\n val2 = ");
    printIEE754(ieee_754_representation_VALUE2);
    printf("= %f",value2);
    printf("\n res = ");
    printIEE754(ieee_754_representation_RESULT);
    printf("= %f \n",result);
    printf("\n");
    
    excecoes();
}
