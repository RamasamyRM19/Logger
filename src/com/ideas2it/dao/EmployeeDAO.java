package com.ideas2it.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.ideas2it.model.Employee;
import com.ideas2it.model.Trainee;
import com.ideas2it.model.Trainer;

/**
 * EmployeeDataAccessObject contains the methods to perform the database
 * operations for Employee Details
 *
 * @version 1.0 12 Aug 2022
 *
 * @author Ramasamy R M
 */
public class EmployeeDAO {

    private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    private Session session = null;

    /**
     * <p>
     * Insert Trainer Details
     * </p>
     *
     * @param Trainer trainer
     * @return
     */
    public Integer insertTrainer(Trainer trainer) throws HibernateException {
        Integer rowsAffected = 0;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            rowsAffected = (Integer) session.save(trainer);
            transaction.commit();
        } finally {
            session.close();
        }
        return rowsAffected;
    }

    /**
     * <p>
     * Get all Trainer details from SQL Databases
     * </p>
     *
     * @param
     * @return List<Trainer> trainers
     */
    public List<Trainer> retrieveAllTrainers() throws HibernateException {
        List<Trainer> trainers = new ArrayList<Trainer>();
        try {
            session = sessionFactory.openSession();
            Query query = session.createQuery("FROM Trainer");
            trainers = query.list();
        } finally {
            session.close(); 
        }
        return trainers;
    }

    /**
     * <p>
     * Get the particular Trainer detail from SQL database
     * </p>
     *
     * @param Integer id
     * @return List<Trainer> trainers
     */
    public Trainer retrieveTrainerById(Integer id) throws HibernateException {
        Trainer trainer = new Trainer();
        try {
            session = sessionFactory.openSession();
            trainer = (Trainer) session.get(Trainer.class, id);
        } finally {
            session.close(); 
        }
        return trainer;
    }

    /**
     * <p>
     * Update the particular trainer detail by id
     * </p>
     *
     * @param Trainer trainer
     * @return void
     */
    public void updateTrainerById(Trainer trainer) throws HibernateException {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            if (!trainer.isEmpty()) {
                session.merge(trainer);
            }
            transaction.commit();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Delete the particular Trainer detail from SQL database
     * </p>
     *
     * @param Integer id
     * @return void
     */
    public void deleteTrainerById(Integer id) throws HibernateException {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainer trainer = (Trainer) session.get(Trainer.class, id);
            session.delete(trainer);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Insert Trainee Details into sql database
     * </p>
     *
     * @param Trainee trainee
     * @return void
     */
    public Integer insertTrainee(Trainee trainee) throws HibernateException {
        Integer rowsAffected = 0;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            rowsAffected = (Integer) session.save(trainee);
            transaction.commit();
        } finally {
            session.close();
        }
        return rowsAffected;
    }

    /**
     * <p>
     * Get all Trainee details from SQL Database
     * </p>
     *
     * @param
     * @return List<Trainee> trainees
     */
    public List<Trainee> retrieveAllTrainees() throws HibernateException {
        List<Trainee> trainees = new ArrayList<Trainee>();
        try {
            session = sessionFactory.openSession();
            String query = "FROM Trainee";
            trainees = session.createQuery(query).list();
        } finally {
            session.close();
        }
        return trainees;
    }

    /**
     * <p>
     * Get the particular Trainee detail from SQL database
     * </p>
     *
     * @param Integer id
     * @return List<Trainee> trainees
     */
    public Trainee retrieveTraineeById(Integer id) {
        Trainee trainee = new Trainee();
        try {
            session = sessionFactory.openSession();
            trainee = (Trainee) session.get(Trainee.class, id);
        } finally {
            session.close();
        }
        return trainee;
    }

    /**
     * <p>
     * Update the particular trainee detail by id
     * </p>
     *
     * @param Trainee trainee
     * @return void
     */
    public void updateTraineeById(Trainee trainee) {
        try { 
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            if (!trainee.isEmpty()) {
                session.merge(trainee);
            }
            transaction.commit();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Delete the particular Trainee detail from SQL database
     * </p>
     *
     * @param Integer id
     * @return void
     */
    public void deleteTraineeById(Integer id) {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Trainee trainee = (Trainee) session.get(Trainee.class, id);
            session.delete(trainee);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    /**
     * <p>
     * Search the particular Trainer detail from SQL database by FirstName
     * </p>
     *
     * @param String detail
     * @return List<Trainer> trainers
     */
    public List<Employee> searchEmployeeByFirstName(String firstName) {
        List<Employee> employee = new ArrayList<Employee>();
        try {
            session = sessionFactory.openSession();
            String hql = "from Employee where firstName like :firstName";
            Query query = session.createQuery(hql);
            query.setParameter("firstName", "%" + firstName + "%");
            employee = query.list();
        } finally {
            session.close();
        }
        return employee;
    }

    /**
     * <p>
     * Search the particular Trainer detail from SQL database by LastName
     * </p>
     *
     * @param String detail
     * @return List<Trainer> trainers
     */
    public List<Employee> searchEmployeeByLastName(String lastName) {
        List<Employee> employee = new ArrayList<Employee>();
        try {
            session = sessionFactory.openSession();
            String hql = "from Employee where lastName like :lastName";
            Query query = session.createQuery(hql);
            query.setParameter("lastName", "%" + lastName + "%");
            employee = query.list();
        } finally {
            session.close();
        }
        return employee;
    }
}