package jpabook.chapter5;

import jpabook.manytoone.Department;
import jpabook.manytoone.Employee;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RelationQueryTest {
   static EntityManagerFactory emf;
   EntityManager em;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpabook");
    }

    @BeforeEach
    void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @Test
    void find() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        Employee result = em.find(Employee.class, 1L);
        System.out.println(result);
    }

    @Test
    void flush_department_before_find() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        em.flush();
        em.clear();

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        Employee result = em.find(Employee.class, 1L);
        System.out.println(result);
    }

    @Test
    void flush_before_find() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        em.flush();
        em.clear();

        Employee result = em.find(Employee.class, 1L);
        System.out.println(result);
    }

    @Test
    void find_by_jpql() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        String jpql = "select e from Employee e";
        List<Employee> results = em.createQuery(jpql, Employee.class)
                .getResultList();

        results.forEach(System.out::println);
    }

    @Test
    void flush_find_by_jpql() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        em.flush();
        em.clear();

        String jpql = "select e from Employee e";
        List<Employee> results = em.createQuery(jpql, Employee.class)
                .getResultList();

        results.forEach(System.out::println);
    }

    @Test
    void flush_find_by_jpql_join() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        em.flush();
        em.clear();

        String jpql = "select e from Employee e join e.department";
        List<Employee> results = em.createQuery(jpql, Employee.class)
                .getResultList();

        results.forEach(System.out::println);
    }

    @Test
    void flush_find_by_jpql_join_fetch() {
        Department department = new Department(1L, "dev team");
        em.persist(department);

        Employee employee = new Employee(1L, "june");
        employee.setDepartment(department);
        em.persist(employee);

        em.flush();
        em.clear();

        String jpql = "select e from Employee e join fetch e.department";
        List<Employee> results = em.createQuery(jpql, Employee.class)
                .getResultList();

        results.forEach(System.out::println);
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
        em.close();
    }

    @AfterAll
    static void afterAll() {
        emf.close();
    }
}
