package com.vaadin.crudui.repositorio;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.crudui.entidades.Category;

/**
 * @author Boniface Chacha
 * @email boniface.chacha@niafikra.com
 * @email bonifacechacha@gmail.com
 * @date 8/30/17 3:30 PM
 */
public interface CategoryRepository  extends JpaRepository<Category,Long> {

    List<Category> findAllByParent(Category parent);

    List<Category> findAllByParentIsNull();

}
