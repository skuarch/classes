package model.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author skuarch
 */
final class HibernateHandleSession {

    private Session session = null;

    //==========================================================================
    protected HibernateHandleSession() {
    }

    //==========================================================================
    /**
     * start session with hibernate.
     *
     * @throws HibernateException if something is wrong
     */
    protected void openSession() {

        try {

            session = HibernateUtil.getSessionFactory().openSession();

        } catch (HibernateException he) {
            closeSession();
            throw he;
        }
    } // end startSession

    //==========================================================================
    protected void commitTransaction() {

        try {

            session.beginTransaction().commit();

        } catch (HibernateException he) {
            closeSession();
            throw he;
        }

    }

    //==========================================================================
    protected void closeSession() {
        HibernateUtil.closeSession(session);
    }

    //==========================================================================
    /**
     * this method return a session, this session may be is not open
     *
     * @return Session
     */
    protected Session getSession() {
        return session;
    }

}
