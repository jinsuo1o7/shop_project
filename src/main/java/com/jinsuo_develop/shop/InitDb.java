package com.jinsuo_develop.shop;

import com.jinsuo_develop.shop.domain.Category;
import com.jinsuo_develop.shop.domain.ProductCategory;
import com.jinsuo_develop.shop.domain.clothes.Clothes;
import com.jinsuo_develop.shop.domain.clothes.ClothesSize;
import com.jinsuo_develop.shop.domain.clothes.Size;
import com.jinsuo_develop.shop.repository.CategoryRepository;
import com.jinsuo_develop.shop.repository.ClothesSizeRepository;
import com.jinsuo_develop.shop.repository.ProductRepository;
import com.jinsuo_develop.shop.repository.SizeRepository;
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

    @PostConstruct
    public void init() {
        initService.initCategory();
        initService.initSizes();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final SizeRepository sizeRepository;
        private final CategoryRepository categoryRepository;
        private final ProductRepository productRepository;
        private final ClothesSizeRepository clothesSizeRepository;

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
    }
}
