package com.jinsuo_develop.shop;

import com.jinsuo_develop.shop.domain.Category;
import com.jinsuo_develop.shop.domain.Product;
import com.jinsuo_develop.shop.domain.ProductCategory;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.ClothesSize;
import com.jinsuo_develop.shop.domain.clothes.Size;
import com.jinsuo_develop.shop.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import static com.jinsuo_develop.shop.domain.clothes.SizeType.*;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;
    private final SizeRepository sizeRepository;
    private final CategoryRepository categoryRepository;

//    @PostConstruct
    public void init() {
        initService.initCategory();
        initService.initSizes();
        initService.initProducts();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final SizeRepository sizeRepository;
        private final CategoryRepository categoryRepository;
        private final ProductRepository productRepository;
        private final ClothesSizeRepository clothesSizeRepository;
        private final ProductCategoryRepository productCategoryRepository;

        public void initSizes() {
            Size xxs = new Size();
            xxs.setSizeType(XXS);
            Size xs = new Size();
            xs.setSizeType(XS);
            Size s = new Size();
            s.setSizeType(S);
            Size m = new Size();
            m.setSizeType(M);
            Size l = new Size();
            l.setSizeType(L);
            Size xl = new Size();
            xl.setSizeType(XL);
            Size xxl = new Size();
            xxl.setSizeType(XXL);

            sizeRepository.save(xxl);
            sizeRepository.save(xl);
            sizeRepository.save(l);
            sizeRepository.save(m);
            sizeRepository.save(s);
            sizeRepository.save(xs);
            sizeRepository.save(xxs);
        }

        public void initCategory() {
            Category manTop = new Category();
            manTop.setName("man top");
            categoryRepository.save(manTop);

            Category womanTop = new Category();
            womanTop.setName("woman top");
            categoryRepository.save(womanTop);

            Category manBottom = new Category();
            manBottom.setName("man bottom");
            categoryRepository.save(manBottom);

            Category womanBottom = new Category();
            womanBottom.setName("woman bottom");
            categoryRepository.save(womanBottom);

            Category man = new Category();
            man.setName("man");
            Category woman = new Category();
            woman.setName("woman");
            man.addChildCategory(manTop);
            man.addChildCategory(manBottom);
            woman.addChildCategory(womanTop);
            woman.addChildCategory(womanBottom);

            categoryRepository.save(man);
            categoryRepository.save(woman);
        }

        public void initProducts() {
            Category category = categoryRepository.findByName("man").orElseThrow();
            Size sizeL = sizeRepository.findBySizeType(L).orElseThrow();
            Size sizeM = sizeRepository.findBySizeType(M).orElseThrow();
            Size sizeS = sizeRepository.findBySizeType(S).orElseThrow();

            for (int i = 0; i < 50; i++) {
                Clothes product = new Clothes();
                product.setImageUrl("https://res.cloudinary.com/dnkichj7u/image/upload/v1677161518/ji4l3fpqetznx28grfqi.jpg");
                product.setName("SAMPLE");
                product.setPrice(10000);
                product.setDescription("sample description");
                productRepository.save(product);

                ClothesSize clothesSizeL = ClothesSize.createClothesSize(product, sizeL, 100);
                ClothesSize clothesSizeM = ClothesSize.createClothesSize(product, sizeM, 200);
                ClothesSize clothesSizeS = ClothesSize.createClothesSize(product, sizeS, 90);
                clothesSizeRepository.save(clothesSizeL);
                clothesSizeRepository.save(clothesSizeM);
                clothesSizeRepository.save(clothesSizeS);

                ProductCategory productCategory = ProductCategory.createProductCategory(product, category);
                productCategoryRepository.save(productCategory);
            }
        }
    }
}
