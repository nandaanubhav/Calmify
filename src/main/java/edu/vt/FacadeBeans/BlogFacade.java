/*
 * Created by Anshika Tyagi, Anubhav Nanda and Het Veera on 2021.12.8
 * Copyright © 2021 Anshika Tyagi, Anubhav Nanda and Het Veera. All rights reserved.
 *
 */
package edu.vt.FacadeBeans;

import edu.vt.EntityBeans.Blog;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Stateless annotation implies that the conversational state with the client shall NOT be maintained.
@Stateless
public class BlogFacade extends AbstractFacade<Blog> {
    /*
    ---------------------------------------------------------------------------------------------
    The EntityManager is an API that enables database CRUD (Create Read Update Delete) operations
    and complex database searches. An EntityManager instance is created to manage entities
    that are defined by a persistence unit. The @PersistenceContext annotation below associates
    the entityManager instance with the persistence unitName identified below.
    ---------------------------------------------------------------------------------------------
     */
    @PersistenceContext(unitName = "Calmify-PU")
    private EntityManager entityManager;

    // Obtain the object reference of the EntityManager instance in charge of
    // managing the entities in the persistence context identified above.
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public int createBlog(Blog blog) {
        getEntityManager().persist(blog);
        return blog.getId();
    }

    public List<Blog> findUserBlogsByUserPrimaryKey(Integer primaryKey) {
        /*
        The following @NamedQuery definition is given in UserFile entity class file:
        @NamedQuery(name = "UserFile.findUserFilesByUserId", query = "SELECT u FROM UserFile u WHERE u.userId.id = :userId")

        The following statement obtains the results from the named database query.
         */
        return entityManager.createNamedQuery("Blog.findBlogsByUserId")
                .setParameter("userId", primaryKey)
                .getResultList();
    }

    public List<Blog> findBlogByPublished(Boolean published) {
        /*
        The following @NamedQuery definition is given in UserFile entity class file:
        @NamedQuery(name = "UserFile.findUserFilesByUserId", query = "SELECT u FROM UserFile u WHERE u.userId.id = :userId")

        The following statement obtains the results from the named database query.
         */
        return entityManager.createNamedQuery("Blog.findBlogsByPublished")
                .setParameter("published", published)
                .getResultList();
    }

    /*
    This constructor method invokes its parent AbstractFacade's constructor method,
    which in turn initializes its entity class type T and entityClass instance variable.
     */
    public BlogFacade() {
        super(Blog.class);
    }


}
