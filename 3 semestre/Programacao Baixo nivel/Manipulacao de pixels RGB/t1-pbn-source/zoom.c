#include <stdio.h>
#include "lib_ppm.h"

int main()
{
    struct image_s data;
    struct image_s data2;
    struct image_s *image = &data;
    struct image_s *image2 = &data2;

    int corR, corG, corB;
    int i, j, r, n;

    r = read_ppm("lena.ppm", image);

    n = new_ppm(image2, 1536, 1536);

    if (r == 0 && n == 0)
    {
        for (j = 0; j < image->height; j++)
        {
            for (i = 0; i < image->width; i++)
            {
                // Recebe os valores de cor originais 
                corR = image->pix[j * image->width + i].r;
                corG = image->pix[j * image->width + i].g;
                corB = image->pix[j * image->width + i].b;

                // Transforma o pixel Red
                if (corR < 75){} // não faz nada
                if (corR > 75 && corR <= 134)
                {
                    image2->pix[(j * 3) * image2->width + i * 3].r = 0;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3].r = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3].r = 0;
                }
                if (corR > 135 && corR <= 179)
                {
                    image2->pix[(j * 3) * image2->width + i * 3].r = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3].r = 0;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3].r = 255;
                }
                if (corR > 180 && corR <= 255)
                {
                    image2->pix[(j * 3) * image2->width + i * 3].r = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3].r = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3].r = 255;
                }

                // Transforma o pixel Green
                if (corG < 75){} // não faz nada
                if (corG > 75 && corG <= 134)
                {
                    image2->pix[(j * 3) * image2->width + i * 1].g = 0;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 1].g = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 1].g = 0;
                }
                if (corG > 135 && corG <= 179)
                {
                    image2->pix[(j * 3) * image2->width + i * 3 + 1].g = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 1].g = 0;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 1].g = 255;
                }
                if (corG > 180 && corG <= 255)
                {
                    image2->pix[(j * 3) * image2->width + i * 3 + 1].g = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 1].g = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 1].g = 255;
                }

                // Transforma o pixel Blue
                if (corB < 75){} // não faz nada
                if (corB > 75 && corB <= 134)
                {
                    image2->pix[(j * 3) *  image2->width + i * 3 + 2].b = 0;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 2].b = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 2].b = 0;
                }
                if (corB > 135 && corB <= 179)
                {
                    image2->pix[(j * 3) * image2->width + i * 3 + 2].b = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 2].b = 0;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 2].b = 255;
                }
                if (corB > 180 && corB <= 255)
                {
                    image2->pix[(j * 3) * image2->width + i * 3 + 2].b = 255;
                    image2->pix[(j * 3 + 1) * image2->width + i * 3 + 2].b = 255;
                    image2->pix[(j * 3 + 2) * image2->width + i * 3 + 2].b = 255;
                }
            }
        }
        write_ppm("zoom3x.ppm", image2);
        free_ppm(image2);
        free_ppm(image);
    }
    return 0;
}